package client.scenes;

import commons.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingleplayerInsteadOfQuestionCtrl {

    @FXML
    private Button exit;

    @FXML
    private Text question1Text;

    @FXML
    private Text question2Text;

    @FXML
    private Text question3Text;

    @FXML
    private Button joker1;

    @FXML
    private Button joker2;

    @FXML
    private Button joker3;

    @FXML
    private Button option1;

    @FXML
    private ImageView option1Image;

    @FXML
    private Button option2;

    @FXML
    private ImageView option2Image;

    @FXML
    private Button option3;

    @FXML
    private ImageView option3Image;

    @FXML
    private Label question;

    @FXML
    private Label score;

    @FXML
    private Label time;

    @FXML
    private Label questionNumber;

    private final MainCtrl mainCtrl;
    private InsteadOfQuestion questionObject;


    @Inject
    public SingleplayerInsteadOfQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * /**
     *      This method initialises all the JFX fields with
     *      attributes of the Question and Player Classes.
     */
    public void initialiseSinglePlayerInsteadOfQuestion() {
        switchButtons(false);
        Game currentGame = mainCtrl.getGame();
        InsteadOfQuestion q =
                (InsteadOfQuestion) currentGame.
                        getQuestions().
                        get(currentGame.getCurrentQuestionNumber());
        questionObject = q;
        Player player = ((SinglePlayerGame) currentGame).getPlayer();
        score.setText(String.valueOf((player.getCurrentScore())));
        Activity activity = q.getActivity();
        question.setText("Instead of " + activity.getTitle());
        ArrayList<Activity> options = q.getOptions();
        Collections.shuffle(options);
        question1Text.setText(String.valueOf(options.get(0).getTitle()));
        question2Text.setText(String.valueOf(options.get(1).getTitle()));
        question3Text.setText(String.valueOf(options.get(2).getTitle()));

        setQuestionNumber("Question " + currentGame.getCurrentQuestionNumber() + "/" +
                (currentGame.getQuestions().size() - 1));

        List<JokerCard> jokerCards = player.getJokerCards();
        initialiseActivityImages(options);
        setJokers(jokerCards);
    }

    /**
     * This method will switch the buttons on or off according to the boolean passed. True means off
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
//    public void option1Handler() {
//        if(questionObject.getOtherActivities().indexOf(getExpensiveActivity()) == 0) {
//            handleCorrect();
//        } else {
//            handleWrong();
//        }
//        switchButtons(true);
//    }

    /**
     * Handles the clicks on button with option 2
     */
//    public void option2Handler() {
//        if(questionObject.getOtherActivities().indexOf(getExpensiveActivity()) == 1) {
// TODO this is the condition that produces true/false based on the answer, this might have to be changed
// //           handleCorrect();
//        } else {
//            handleWrong();
//        }
//        switchButtons(true);
//    }

    /**
     * Handles the clicks on button with option 3
     */
//    public void option3Handler() {
//        if(questionObject.getOtherActivities().indexOf(getExpensiveActivity()) == 2) {
//            handleCorrect();
//        } else {
//            handleWrong();
//        }
//        switchButtons(true);
//    }

    /**
     * This method will handle when the user click the correct option. For the moment it is increasing the points of the
     * player and printing out correct
     */
    void handleCorrect() {
        Player p = ((SinglePlayerGame) mainCtrl.getGame()).getPlayer();
        p.setCurrentScore(p.getCurrentScore() + questionObject.getAvailablePoints());
        System.out.println("correct");
    }

    /**
     * This method will handle when the user clicks the wrong option. For the moment it is only printing wrong on the
     * console
     */
    void handleWrong() {
        System.out.println("wrong");
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

    private void initialiseActivityImages(List<Activity> activityList) {
        String  server = "http://localhost:8080/";

        option1Image.setImage(new Image(server + activityList.get(0).getImage_path()));
        option2Image.setImage(new Image(server + activityList.get(1).getImage_path()));
        option3Image.setImage(new Image(server + activityList.get(2).getImage_path()));
    }

    @FXML
    protected void goToMenu() {
        mainCtrl.setExitedGame(true);
        mainCtrl.goTo("menu");
    }


    @FXML
    public void setTime(int i) {
        time.setText("Time Left: " + String.valueOf(i));
    }

    public void setQuestionNumber(String i) {
        questionNumber.setText(i);
    }

}
