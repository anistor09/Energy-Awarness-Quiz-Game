package client.scenes;

import com.google.inject.Inject;
import commons.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.List;

public class SinglePlayerOpenQuestionCtrl {

    @FXML
    private Button exit;

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
    private Label time;

    @FXML
    private TextField userAnswer;
    private final MainCtrl mainCtrl;

    private GuessQuestion questionObject;

    @Inject
    public SinglePlayerOpenQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    @FXML
    void exit(ActionEvent event) {
        mainCtrl.goTo("menu");
    }


    /**
     * This method initialises all the JFX fields with attributes of the Question and Player Classes.
     */
    public void initialiseSinglePlayerOpenQuestion() {
        switchButtons(false);
        Game currentGame = mainCtrl.getGame();
        GuessQuestion q = (GuessQuestion)currentGame.getQuestions().
                get(currentGame.getCurrentQuestionNumber());
        questionObject = q;
        Player player = ((SinglePlayerGame)currentGame).getPlayer();
        score.setText(String.valueOf(player.getCurrentScore()));
        Activity act = q.getActivity();
        question.setText(String.valueOf(act.getTitle()));

        List<JokerCard> jokerList = player.getJokerCards();
        if(jokerList.size()>=1)
        {
            joker1.setText(jokerList.get(0).getName());
            if(jokerList.size()>=2)
            {
                joker2.setText(jokerList.get(1).getName());
                if(jokerList.size()>=3)
                {
                    joker3.setText(jokerList.get(2).getName());


                }

            }
        }

    }

    public void setTime(int i) {
        time.setText(String.valueOf(i));
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
            System.out.println(guess);
            System.out.println(points);
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
}
