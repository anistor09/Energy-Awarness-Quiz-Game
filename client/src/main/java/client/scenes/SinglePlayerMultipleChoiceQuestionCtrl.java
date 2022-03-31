package client.scenes;

import client.utils.ServerUtils;
import commons.*;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javax.inject.Inject;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


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
    private Label questionNumber;

    @FXML
    private Label time;

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

        setQuestionNumber("Question " + currentGame.getCurrentQuestionNumber() + "/" +
                (currentGame.getQuestions().size() - 1));

        List<JokerCard> jokerList = player.getJokerCards();
        this.setJokers(jokerList);
        jokerMessage.setText("");
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
     * Handles the clicks on button with option 1
     */
    public void option1Handler() {
        if (questionObject.getOptions().indexOf((double) questionObject.getActivity().getConsumption_in_wh()) == 0) {
            handleCorrect();
        } else {
            handleWrong();
        }
        switchButtons(true);
    }

    /**
     * Handles the clicks on button with option 1
     */
    public void option2Handler() {
        if (questionObject.getOptions().indexOf((double) questionObject.getActivity().getConsumption_in_wh()) == 1) {
            handleCorrect();
        } else {
            handleWrong();
        }
        switchButtons(true);
    }

    /**
     * Handles the clicks on button with option 1
     */
    public void option3Handler() {
        if (questionObject.getOptions().indexOf((double) questionObject.getActivity().getConsumption_in_wh()) == 2) {
            handleCorrect();
        } else {
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
        Player p = null;
        if(game instanceof SinglePlayerGame) {
            p = ((SinglePlayerGame) game).getPlayer();
        } else {
            MultiPlayerGame m = (MultiPlayerGame) game;
            for(int i = 0; i < m.getPlayers().size(); i++) {
                Player localPlayer = mainCtrl.getLocalPlayer();
                Player toSearch = m.getPlayers().get(i);
                if(toSearch.getUsername().equals(localPlayer.getUsername())) {
                    p = m.getPlayers().get(i);
                }
            }
        }
        System.out.println("correct");
        int timeAfterQuestionStart = questionObject.getAllowedTime() - MainCtrl.getTimeLeft();
        double quotient = (double) timeAfterQuestionStart / (double) questionObject.getAllowedTime();
        int points = (int) ((1 - 0.5 * quotient) * questionObject.getAvailablePoints());
        p.setCurrentScore(p.getCurrentScore() + points);
        mainCtrl.getLocalPlayer().setCurrentScore(p.getCurrentScore());
        if(game instanceof MultiPlayerGame) {
            server.updatePlayerScore(new Player(p.getUsername(), p.getCurrentScore()), mainCtrl.getGameId());
        }
        IntermediateScreenCtrl.setPointsGained(points);


    }

    /**
     * This method will handle when the user clicks the wrong option. For the moment it is only printing wrong on the
     * console
     */
    void handleWrong() {
        System.out.println("wrong");
        IntermediateScreenCtrl.setPointsGained(0);
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
        if (canUseJoker(joker3.getText())) {
            jokerMessage.setText("");
            mainCtrl.setUsedJoker(joker3.getText());
            joker3.setDisable(true);
            mainCtrl.handleJoker();
        }
        else{
            jokerMessage.setText("This joker cannot be used in this type of question!");
        }
    }
    public boolean canUseJoker(String name){
        return true;
    }

    public void setTime(int i) {
        time.setText("Time Left: " + String.valueOf(i));
    }
    @FXML
    public void exit() {
        mainCtrl.setExitedGame(true);
        mainCtrl.goTo("menu");
    }

    public void setQuestionNumber(String i) {
        questionNumber.setText(i);
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
}

