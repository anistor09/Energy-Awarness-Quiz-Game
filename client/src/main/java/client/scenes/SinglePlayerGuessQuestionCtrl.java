package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import commons.*;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SinglePlayerGuessQuestionCtrl implements Initializable {


    private ServerUtils server;
    @FXML
    private Button exit;

    @FXML
    private HBox emojiBar;

    @FXML
    private Label jokerMessage;
    @FXML
    private Label ReactionName;

    @FXML
    private ImageView anger;
    @FXML
    private ImageView crying;

    @FXML
    private ImageView devil;
    @FXML
    private ImageView inLove;
    @FXML
    private ImageView reaction;
    @FXML
    private ImageView smiling;

    @FXML
    private ImageView thinking;

    @FXML
    private ImageView image;

    @FXML
    private ImageView joker;

    @FXML
    private Button joker1;

    @FXML
    private Button joker2;

    @FXML
    private Button joker3;

    @FXML
    private Label question;

    @FXML
    private Label score;
    @FXML
    private Text questionText;

    @FXML
    private Label questionNumber;

    @FXML
    private Label actualAnswer;

    @FXML
    private TextField userAnswer;

    @FXML
    private Rectangle timeBar;

    private int timeBarWidth = 950;

    private final MainCtrl mainCtrl;

    private GuessQuestion questionObject;

    private static int pointsGained = 0;

    private IntermediateScreenCtrl intermediateScreenCtrl;

    @Inject
    public SinglePlayerGuessQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = mainCtrl.getServer();
    }

    @FXML
    void exit(ActionEvent event) {
        mainCtrl.setExitedGame(true);
        mainCtrl.goTo("menu");
    }


    /**
     * This method initialises all the JFX fields with attributes of the Question and Player Classes.
     * Goes to the intermediate screen after X seconds where X is the maximum allowed time.
     */
    public void initialiseSinglePlayerOpenQuestion() {
        resetScreen();
        switchButtons(false);
        Game currentGame = mainCtrl.getGame();
        this.setEmojiBarVisible(currentGame);
        GuessQuestion q = (GuessQuestion)currentGame.getQuestions().
                get(currentGame.getCurrentQuestionNumber());
        questionObject = q;
        Player player = mainCtrl.getLocalPlayer();
        score.setText(String.valueOf(player.getCurrentScore()));
        Activity act = q.getActivity();
        question.setText("How much energy does it take?");
        questionText.setText(act.getTitle());
        jokerMessage.setText("");
        initialiseActivityImage(act);

        setQuestionNumber("Question " + currentGame.getCurrentQuestionNumber() + "/" +
                (currentGame.getQuestions().size() - 1));

        List<JokerCard> jokerList = player.getJokerCards();
        this.setJokers(jokerList);

    }

    public void resetScreen(){
        userAnswer.setText("");
        actualAnswer.setText("");
        timeBar.setWidth(950);
        timeBar.setFill(Color.valueOf("#00FF00"));
    }

    /**
     * This method maps the player's jokers to their corresponding buttons
     * @param jokerList List of JokerCard instances representing the player's jokers
     */
    public void setJokers(List<JokerCard> jokerList){
       Button[] buttonArray ={ joker1,joker2,joker3};

       for(int i=0;i<buttonArray.length;i++){
           Button current = buttonArray[i];
           if(i<=jokerList.size()-1){

               current.setText(jokerList.get(i).getName());
           }
           else{
               current.setText("Unavailable Joker");
               current.setDisable(true);
           }

       }
    }
    /**
     * This method initialises the Image view with the corresponding image of the activity
     * @param act Instance of Activity
     */
    private void initialiseActivityImage(Activity act) {
        String  server = "http://localhost:8080/";
        Image img = new Image(server + act.getImage_path());

        image.setImage(img);
    }

    public void startTimerAnimation() {
        Timer animationTimer = new Timer();
        animationTimer.scheduleAtFixedRate(new TimerTask() {
            double p = timeBarWidth;
            double w = (double) mainCtrl.getGame().getQuestions().get(mainCtrl.getGame().getCurrentQuestionNumber()).
                    getAllowedTime();
            int j = mainCtrl.getGame().getQuestions().get(mainCtrl.getGame().getCurrentQuestionNumber()).
                    getAllowedTime();
            @Override
            public void run() {
                if(w < 0){
                    animationTimer.cancel();
                }

                double timerBarLengthRatio = w/j;

                if ((timerBarLengthRatio <= 0.5) && (timerBarLengthRatio > 0.25)) {
                    timeBar.setFill(Color.valueOf("#FFFF00"));
                }
                if ((timerBarLengthRatio <= 0.25) && (timerBarLengthRatio > 0.125)) {
                    timeBar.setFill(Color.valueOf("#FFA500"));
                }
                if (timerBarLengthRatio <= 0.125) {
                    timeBar.setFill(Color.valueOf("#FF0000"));
                }
                timeBar.setWidth(p*timerBarLengthRatio);
                w = w - 0.1;
            }
        },0,100);
    }

    /**
     * This is the onAction method for the Label. When the user hits enter this will be called. It will either
     * calculate the number of points in case it is an integer or clear the field in case an exception is thrown.
     * All the buttons are disabled after the call
     */
    public void changeGuess() {
        try {
            long guess = Long.parseLong(userAnswer.getCharacters().toString());
            switchButtons(true);
            int points = questionObject.calculatePoints(guess);
            Player p = ((SinglePlayerGame) mainCtrl.getGame()).getPlayer();
            p.setCurrentScore(p.getCurrentScore() + points);
            if (points == 100) {
                actualAnswer.setText("Bullseye! As you answered, the actual consumption for this activity is " +
                        questionObject.getActivity().getConsumption_in_wh() + "wh");
            }
            else if (points > 70) {
                actualAnswer.setText("Close! The actual consumption for this activity is " +
                        questionObject.getActivity().getConsumption_in_wh() + "wh");
            } else if (points > 0) {
                actualAnswer.setText("Not quite! The actual consumption for this activity is " +
                        questionObject.getActivity().getConsumption_in_wh() + "wh");
            } else {
                actualAnswer.setText("Nowhere near! The actual consumption for this activity is " +
                        questionObject.getActivity().getConsumption_in_wh()+ "wh");
            }
            if(mainCtrl.getGame() instanceof MultiPlayerGame) {
                server.updatePlayerScore(new Player(p.getUsername(), p.getCurrentScore()), mainCtrl.getGameId());
            }
            IntermediateScreenCtrl.setPointsGained(points);
        } catch (Exception e) {
            userAnswer.clear();
            System.out.println("Not a number");
        }
    }

    /**
     * This method will switch the buttons on or off according to the boolean passed. True means off
     * @param onOff the boolean for which to set the setDisable property
     */

    void switchButtons(boolean onOff) {
        userAnswer.setDisable(onOff);
        joker1.setDisable(onOff);
        joker2.setDisable(onOff);
        joker3.setDisable(onOff);
    }

    public int getPointsGained() {
        return pointsGained;
    }

    public void setPointsGained(int pointsGained) {
        this.pointsGained = pointsGained;
    }
    @FXML
    void handleJokerButton1() {
        if(canUseJoker(joker1.getText())) {
            jokerMessage.setText("");
            mainCtrl.setUsedJoker(joker1.getText());
            joker1.setDisable(true);
            mainCtrl.handleJoker();
        }
        else{
            jokerMessage.setText("This joker cannot be used in this type of question!");
        }
    }
    @FXML
    void handleJokerButton2() {
        if(canUseJoker(joker2.getText())) {
            jokerMessage.setText("");
            mainCtrl.setUsedJoker(joker2.getText());
            joker2.setDisable(true);
            mainCtrl.handleJoker();
        }
        else{
            jokerMessage.setText("This joker cannot be used in this type of question!");
        }
    }
    @FXML
    void handleJokerButton3() {
        jokerMessage.setText("");
        if (canUseJoker(joker3.getText())) {
            mainCtrl.setUsedJoker(joker3.getText());
            joker3.setDisable(true);
            mainCtrl.handleJoker();
        }
        else{
            jokerMessage.setText("This joker cannot be used in this type of question!");
        }
    }
    public boolean canUseJoker(String name){
        if(name.equals("EliminateOptionJoker"))
            return false;
        return true;
    }

    public void setQuestionNumber(String i) {
        questionNumber.setText(i);
    }
    /**
     * This method send the Emoji to the other clients through WebSockets.
     * @param e Instance of Emoji Class that contains an emoji with the Player's username and it's image path.
     */
    public void sendEmoji(Emoji e){
        server.send("/app/emojis/"+mainCtrl.getGameId(),e);
    }
    /**
     * This  method creates an Emoji and passes it to the sendEmoji() method
     * @param event Event that occurs when an image view for Emoji is pressed.
     */
    public void getEmoji(Event event){
        Emoji e =  new Emoji(mainCtrl.getLocalPlayer().getUsername(),((ImageView)event.getSource()).
                getImage().getUrl());
        sendEmoji(e);
    }

    /**
     * This method initialises the Scene with the last Emoji that was sent through the WebSocket.
     * @param e Instance of Emoji Class( sent through the WebSocket for Emoji Class)
     */
    public void initialiseEmoji(Emoji e) {
        ReactionName.setText(e.getSender());
        reaction.setImage(new Image(e.getEmojiPath()));
        ScaleTransition scale = new ScaleTransition(Duration.millis(50),reaction);
        scale.setToX(1);
        scale.setToY(1);
        scale.setFromX(0.75);
        scale.setFromY(0.75);
        scale.play();
    }
    /**
     * This method initialises the Emojis images because they are not rendered directly for Windows users.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        smiling.setImage(new Image(MainCtrl.class.getResource("/pictures/smilingTeeth.png").toString()));
        anger.setImage(new Image(MainCtrl.class.getResource("/pictures/anger.png").toString()));
        devil.setImage(new Image(MainCtrl.class.getResource("/pictures/devil.png").toString()));
        inLove.setImage(new Image(MainCtrl.class.getResource("/pictures/in-love.png").toString()));
        thinking.setImage(new Image(MainCtrl.class.getResource("/pictures/thinking.png").toString()));
        crying.setImage(new Image(MainCtrl.class.getResource("/pictures/crying.png").toString()));

    }
    private void setEmojiBarVisible(Game currentGame) {
        if(currentGame instanceof MultiPlayerGame){
            emojiBar.setVisible(true);
            Platform.runLater(()->{
                reaction.setImage(null);
                ReactionName.setText("");});
        }
        else{
            emojiBar.setVisible(false);
        }
    }

    public void hideEmoji() {
        emojiBar.setVisible(false);
    }

}
