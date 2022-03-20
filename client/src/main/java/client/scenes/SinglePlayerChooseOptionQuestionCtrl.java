package client.scenes;

import com.google.inject.Inject;
import commons.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.List;

public class SinglePlayerChooseOptionQuestionCtrl {

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

    private final MainCtrl mainCtrl;
    private MostEnergyQuestion questionObject; //the object that is being displayed

    @Inject
    public SinglePlayerChooseOptionQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * This method initialises all the JFX fields with attributes of the Question and Player Classes.
     * Goes to the intermediate screen after X seconds where X is the maximum allowed time.
     */
    public void initialiseMostEnergyQuestion() {
        switchButtons(false);
        Game currentGame = mainCtrl.getGame();
        MostEnergyQuestion q = (MostEnergyQuestion) currentGame.getQuestions().
                get(currentGame.getCurrentQuestionNumber());
        questionObject = q;
        Player player = ((SinglePlayerGame) currentGame).getPlayer();
        score.setText(String.valueOf(player.getCurrentScore()));
        List<Activity> actList = q.getOtherActivities();
        actList.add(q.getActivity());
        question1Text.setText(actList.get(0).getTitle());
        question2Text.setText(actList.get(1).getTitle());
        question3Text.setText(actList.get(2).getTitle());
//
        question.setText("What requires more energy?");

        List<JokerCard> jokerList = player.getJokerCards();

       setJokers(jokerList);
    }

    /**
     * This method initialises the Image views with the corresponding image of the activities
     * @param activityList List of instances of the Activity Class
     */
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

    private void initialiseActivityImages(List<Activity> activityList) {
       String  server = "http://localhost:8080/";

        option1Image.setImage(new Image(server + activityList.get(0).getImage_path()));
        option2Image.setImage(new Image(server + activityList.get(1).getImage_path()));
        option3Image.setImage(new Image(server + activityList.get(2).getImage_path()));
    }


    /**
     * Handles the clicks on button with option 1
     */
    public void option1Handler() {
        if(questionObject.getOtherActivities().indexOf(questionObject.getCorrectAnswer()) == 0) {
            handleCorrect();
        } else {
            handleWrong();
        }
        switchButtons(true);
    }

    /**
     * Handles the clicks on button with option 2
     */
    public void option2Handler() {
        if(questionObject.getOtherActivities().indexOf(questionObject.getCorrectAnswer()) == 1) {
            handleCorrect();
        } else {
            handleWrong();
        }
        switchButtons(true);
    }

    /**
     * Handles the clicks on button with option 3
     */
    public void option3Handler() {
        if(questionObject.getOtherActivities().indexOf(questionObject.getCorrectAnswer()) == 2) {
            handleCorrect();
        } else {
            handleWrong();
        }
        switchButtons(true);
    }

    @FXML
    void exit(ActionEvent event) {
        mainCtrl.goTo("menu");
    }

    public void setTime(int i) {
        time.setText(String.valueOf(i));
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
}

