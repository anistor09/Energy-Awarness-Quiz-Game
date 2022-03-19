package client.scenes;

import commons.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;


public class SinglePlayerGameCtrl {

    @FXML
    private Button exit;

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
    private Label time;

    private final MainCtrl mainCtrl;

    /**
     *
     * @param mainCtrl
     */
    @Inject
    public SinglePlayerGameCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;

    }

    /**
     * This method initialises all the JFX fields with attributes of the Question and Player Classes.
     */
    public void initialiseSinglePlayerQuestion(){
        Game currentGame = mainCtrl.getGame();
        MultipleChoiceQuestion q = (MultipleChoiceQuestion)currentGame.getQuestions().
                get(currentGame.getCurrentQuestionNumber());
        Player player = ((SinglePlayerGame)currentGame).getPlayer();
        score.setText(String.valueOf(player.getCurrentScore()));
        Activity act = q.getActivity();
        question.setText(String.valueOf(act.getTitle()));
        List options = q.getOptions();
        Collections.shuffle(options);
        option1.setText(String.valueOf(options.get(0)));
        option2.setText(String.valueOf(options.get(1)));
        option3.setText(String.valueOf(options.get(2)));

        initialiseActivityImage(act);

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

    /**
     * This method initialises the Image view with the corresponding image of the activity
     * @param act Instance of Activity
     */
    private void initialiseActivityImage(Activity act) {
        String  server = "http://localhost:8080/";

        image.setImage(new Image(server + act.getImage_path()));
    }

    public void exit() {
        mainCtrl.goTo("menu");
    }
}
