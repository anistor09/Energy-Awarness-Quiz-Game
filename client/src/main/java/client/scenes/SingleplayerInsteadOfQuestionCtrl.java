package client.scenes;

import commons.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class SingleplayerInsteadOfQuestionCtrl {

    @FXML
    private Button exit;

    @FXML
    private Button joker1;

    @FXML
    private Button joker2;

    @FXML
    private Button joker3;

    @FXML
    private ImageView icon1;

    @FXML
    private ImageView icon2;

    @FXML
    private ImageView icon3;

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
    private Label time;

    private final MainCtrl mainCtrl;


    @Inject
    public SingleplayerInsteadOfQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * /**
     *      This method initialises all the JFX fields with
     *      attributes of the Question and Player Classes.
     */
    public void initializeSinglePlayerInsteadOfQuestion() {
        Game currentGame = mainCtrl.getGame();
        InsteadOfQuestion q =
                (InsteadOfQuestion) currentGame.
                        getQuestions().
                        get(currentGame.getCurrentQuestionNumber());
        Player player = ((SinglePlayerGame) currentGame).getPlayer();
        score.setText(String.valueOf((player.getCurrentScore())));
        Activity activity = q.getActivity();
        question.setText(String.valueOf((activity.getTitle())));
        List options = q.getOptions();
        Collections.shuffle(options);
        option1.setText(String.valueOf(options.get(0)));
        option2.setText(String.valueOf(options.get(1)));
        option3.setText(String.valueOf(options.get(2)));

        List<JokerCard> jokerCards = player.getJokerCards();
        if (jokerCards.size() >= 1) {
            joker1.setText(jokerCards.get(0).getName());
            if (jokerCards.size() >= 2) {
                joker2.setText(jokerCards.get(1).getName());
                if (jokerCards.size() >= 3) {
                    joker3.setText(jokerCards.get(2).getName());
                }
            }
        }


    }

    @FXML
    protected void goToMenu() {
        mainCtrl.goTo("menu");
    }
}