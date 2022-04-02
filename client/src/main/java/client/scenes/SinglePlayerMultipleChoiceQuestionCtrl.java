package client.scenes;

import client.utils.ServerUtils;
import commons.*;
import javafx.application.Platform;
import javafx.animation.ScaleTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import javax.inject.Inject;
import java.net.URL;
import java.util.*;


public class SinglePlayerMultipleChoiceQuestionCtrl implements Initializable {

    private  ServerUtils server;
    @FXML
    private Button exit;

    @FXML
    private HBox emojiBar;

    @FXML
    private Label jokerMessage;
    @FXML
    private Label ReactionName;
    @FXML
    private Label jokerAlertMessage;

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
    private ImageView icon1;

    @FXML
    private ImageView icon2;

    @FXML
    private ImageView icon3;

    @FXML
    private ImageView image;

    @FXML
    private Button joker1;

    @FXML
    private Button joker2;

    @FXML
    private Button joker3;

    @FXML
    private Button option1;

    @FXML
    private Button option2;

    @FXML
    private Button option3;

    @FXML
    private Label question;

    @FXML
    private Label score;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label debug;

    private Rectangle timeBar;
    @FXML
    private int timeBarWidth = 950;


    private final MainCtrl mainCtrl;
    private MultipleChoiceQuestion questionObject;

    private static int pointsGained; // this is the points gained from this question.

    private IntermediateScreenCtrl intermediateScreenCtrl;


    /**
     * @param mainCtrl
     */
    @Inject
    public SinglePlayerMultipleChoiceQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = mainCtrl.getServer();
    }

    /**
     * This method initialises all the JFX fields with attributes of the Question and Player Classes.
     * Goes to the intermediate screen after X seconds where X is the maximum allowed time.
     */
    public void initialiseSinglePlayerQuestion() {
        resetScreen();
        switchButtons(false);
        Game currentGame = mainCtrl.getGame();
        this.setEmojiBarVisible(currentGame);
        MultipleChoiceQuestion q = (MultipleChoiceQuestion) currentGame.getQuestions().
                get(currentGame.getCurrentQuestionNumber());
        Player player = mainCtrl.getLocalPlayer();
        questionObject = q;
        score.setText(String.valueOf(player.getCurrentScore()));
        Activity act = q.getActivity();
        question.setText(String.valueOf(act.getTitle()));
        List options = q.getOptions();
        Collections.shuffle(options);
        option1.setText(String.valueOf(options.get(0)));
        option2.setText(String.valueOf(options.get(1)));
        if (options.size() == 3) {
            option3.setText(String.valueOf(options.get(2)));
        } else {
            option3.setText("Wrong Option");
        }


        initialiseActivityImage(act);

        setQuestionNumber(currentGame.getCurrentQuestionNumber());

        List<JokerCard> jokerList = player.getJokerCards();
        this.setJokers(jokerList);
        jokerMessage.setText("");
        jokerAlertMessage.setText("");
    }

    private void resetScreen() {
        option1.setStyle("-fx-background-color: #8ECAE6");
        option2.setStyle("-fx-background-color: #8ECAE6");
        option3.setStyle("-fx-background-color: #8ECAE6");
        timeBar.setWidth(950);
        timeBar.setFill(Color.valueOf("#00FF00"));
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

    /**
     * This method maps the player's jokers to their corresponding buttons
     *
     * @param jokerList List of JokerCard instances representing the player's jokers
     */
    public void setJokers(List<JokerCard> jokerList) {
        Button[] buttonArray = {joker1, joker2, joker3};

        for (int i = 0; i < buttonArray.length; i++) {
            Button current = buttonArray[i];
            if (i <= jokerList.size() - 1) {
                System.out.println(jokerList.get(i).getName());
                current.setText(jokerList.get(i).getName());
            } else {
                current.setText("Unavailable Joker");
                current.setDisable(true);
            }

        }
    }

    /**
     * This method initialises the Image view with the corresponding image of the activity
     *
     * @param act Instance of Activity
     */
    private void initialiseActivityImage(Activity act) {
        String serverString = server.getServer();

        image.setImage(new Image(serverString + act.getImage_path()));
    }

    /**
     * This method will switch the buttons on or off according to the boolean passed. True means off
     *
     * @param onOff the boolean for which to set the setDisable property
     */
    void switchButtons(boolean onOff) {
        option1.setDisable(onOff);
        option2.setDisable(onOff);
        option3.setDisable(onOff);
        joker1.setDisable(onOff);
        joker2.setDisable(onOff);
        joker3.setDisable(onOff);
    }

    /**
     * Changes a button's background colour to the colour specified.
     * @param button Button whose colour needs to be changed.
     * @param colour Colour to change to.
     */
    private void changeButtonColours(Button button, String colour) {
        if (colour.equals("green")) {
            button.setStyle("-fx-background-color: green");
        } else {
            button.setStyle("-fx-background-color: red");
        }
    }

    // method to locate the correct answer


    /**
     * Handles the clicks on button with option 1
     */
    public void option1Handler() {
        if (questionObject.getOptions().indexOf(questionObject.getActivity().getConsumption_in_wh()) == 0) {
            changeButtonColours(option1, "green");
            handleCorrect();
        } else {
            changeButtonColours(option1, "red");
            handleWrong();
        }
        switchButtons(true);
    }

    /**
     * Handles the clicks on button with option 1
     */
    public void option2Handler() {
        if (questionObject.getOptions().indexOf(questionObject.getActivity().getConsumption_in_wh()) == 1) {
            changeButtonColours(option2, "green");
            handleCorrect();
        } else {
            changeButtonColours(option2, "red");
            handleWrong();
        }
        switchButtons(true);
    }

    /**
     * Handles the clicks on button with option 1
     */
    public void option3Handler() {
        if (questionObject.getOptions().indexOf(questionObject.getActivity().getConsumption_in_wh()) == 2) {
            changeButtonColours(option3, "green");
            handleCorrect();
        } else {
            changeButtonColours(option3, "red");
            handleWrong();
        }
        switchButtons(true);
    }

    /**
     * This method will handle when the user click the correct option. For the moment it is increasing the points of the
     * player and printing out correct
     */
    void handleCorrect() {
        Game game = mainCtrl.getGame();
        questionObject = (MultipleChoiceQuestion) game.getQuestions().get(game.getCurrentQuestionNumber());
        Player p = null;
        if(game instanceof SinglePlayerGame) {
            p = ((SinglePlayerGame) game).getPlayer();
            int timeAfterQuestionStart = questionObject.getAllowedTime() - MainCtrl.getTimeLeft();
            double quotient = (double) timeAfterQuestionStart / (double) questionObject.getAllowedTime();
            int points = (int) ((1 - 0.5 * quotient) * questionObject.getAvailablePoints());
            p.setCurrentScore(p.getCurrentScore() + points);
            IntermediateScreenCtrl.setPointsGained(points);
        } else {
            MultiPlayerGame m = (MultiPlayerGame) game;
            int tl = 0;
            for(int i = 0; i < m.getPlayers().size(); i++) {
                Player localPlayer = mainCtrl.getLocalPlayer();
                tl = localPlayer.getTimeLeft();
                Player toSearch = m.getPlayers().get(i);
                if(toSearch.getUsername().equals(localPlayer.getUsername())) {
                    p = m.getPlayers().get(i);
                }
            }
            // we now have player
            int timeAfterQuestionStart = questionObject.getAllowedTime() - tl;
            double quotient = (double) timeAfterQuestionStart / (double) questionObject.getAllowedTime();
            int points = (int) ((1 - 0.5 * quotient) * questionObject.getAvailablePoints());
            p.setCurrentScore(p.getCurrentScore() + points);
        }

        mainCtrl.getLocalPlayer().setCurrentScore(p.getCurrentScore());
        if(game instanceof MultiPlayerGame) {
            server.updatePlayerScore(new Player(p.getUsername(), p.getCurrentScore()), mainCtrl.getGameId());
        }

    }

    /**
     * This method will handle when the user clicks the wrong option. For the moment it is only printing wrong on the
     * console
     */
    void handleWrong() {
        if (questionObject.getOptions().indexOf(questionObject.getActivity().getConsumption_in_wh()) == 0) {
            changeButtonColours(option1, "green");
        } else if(questionObject.getOptions().indexOf(questionObject.getActivity().getConsumption_in_wh()) == 1) {
            changeButtonColours(option2, "green");
        } else {
            changeButtonColours(option3, "green");
        }
        IntermediateScreenCtrl.setPointsGained(0);
        questionObject.setChosenAnswerCorrect(false);
    }

    @FXML
    void handleJokerButton1() {
        if(canUseJoker(joker1.getText())) {
            jokerMessage.setText("");
            mainCtrl.setUsedJoker(joker1.getText());
            mainCtrl.handleJoker();
            joker1.setDisable(true);
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
            mainCtrl.handleJoker();
            joker2.setDisable(true);
        }
        else{
            jokerMessage.setText("This joker cannot be used in this type of question!");
        }
    }
    @FXML
    void handleJokerButton3() {
        if (canUseJoker(joker3.getText())) {
            jokerMessage.setText("");
            mainCtrl.setUsedJoker(joker3.getText());
            mainCtrl.handleJoker();
            joker3.setDisable(true);
        }
        else{
            jokerMessage.setText("This joker cannot be used in this type of question!");
        }
    }
    public boolean canUseJoker(String name){
        return true;
    }

    /**
     * This method starts the animation for the timer bar
     */
    public void startTimerAnimation() {
        int i = mainCtrl.getGame().getQuestions().get(mainCtrl.getGame().getCurrentQuestionNumber()).getAllowedTime();
        int colourChange1 = (int) (i*1000*0.25);
        int colourChange2 = (int) (i*1000*0.5);
        int colourChange3 = (int) (i*1000*0.75);

        ScaleTransition timerAnimation = new ScaleTransition(Duration.seconds(i), timeBar);
        timerAnimation.setFromX(1);
        timerAnimation.setToX(0);
        timerAnimation.play();
        Timer changeTimerBarColor = new Timer();
        changeTimerBarColor.schedule(new TimerTask() {
            @Override
            public void run() {
                timeBar.setFill(Color.valueOf("#FFFF00"));
            }
        }, colourChange1);
        changeTimerBarColor.schedule(new TimerTask() {
            @Override
            public void run() {
                timeBar.setFill(Color.valueOf("#FFA500"));
            }
        },colourChange2);
        changeTimerBarColor.schedule(new TimerTask() {
            @Override
            public void run() {
                timeBar.setFill(Color.valueOf("#FF0000"));
            }
        },colourChange3);
    }

    @FXML
    public void exit() {
        mainCtrl.setExitedGame(true);
        mainCtrl.goTo("menu");
    }

    public void setQuestionNumber(int i) {
        double progress = (double) i / 20.0;
        progressBar.setProgress(progress);
    }

    public int getPointsGained() {
        return pointsGained;
    }

    public void setPointsGained(int pointsGained) {
        this.pointsGained = pointsGained;
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

    public void hideEmoji() {
        emojiBar.setVisible(false);
    }

    public void initialisejokerAlert(JokerAlert jokerAlert) {
        jokerAlertMessage.setText(jokerAlert.getSenderUsername()+" used "+jokerAlert.getJokerType());
    }
}
