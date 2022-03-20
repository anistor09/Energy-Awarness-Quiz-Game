package client.scenes;

import com.google.inject.Inject;
import commons.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.List;

public class SinglePlayerChooseOptionQuestionCtrl {

    @FXML
    private Button exit;

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
        option1.setText(actList.get(0).getTitle());
        option2.setText(actList.get(1).getTitle());
        option3.setText(actList.get(2).getTitle());
        question.setText("What requires more energy?");

        List<JokerCard> jokerList = player.getJokerCards();

        if (jokerList.size() >= 1) {
            joker1.setText(jokerList.get(0).getName());
            if (jokerList.size() >= 2) {
                joker2.setText(jokerList.get(1).getName());
                if (jokerList.size() >= 3) {
                    joker3.setText(jokerList.get(2).getName());

                }
            }
        }
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

