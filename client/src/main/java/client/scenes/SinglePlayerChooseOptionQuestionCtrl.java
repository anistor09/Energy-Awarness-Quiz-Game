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

    @Inject
    public SinglePlayerChooseOptionQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * This method initialises all the JFX fields with attributes of the Question and Player Classes.
     *
     */
    @FXML
    public void initialiseMostEnergyQuestion(){
        Game currentGame = mainCtrl.getGame();
        MostEnergyQuestion q = (MostEnergyQuestion)currentGame.getQuestions().
                get(currentGame.getCurrentQuestionNumber());
        Player player = ((SinglePlayerGame)currentGame).getPlayer();
        score.setText(String.valueOf(player.getCurrentScore()));
        List<Activity> actList = q.getOtherActivities();
        actList.add(q.getActivity());
        question1Text.setText(actList.get(0).getTitle());
        question2Text.setText(actList.get(1).getTitle());
        question3Text.setText(actList.get(2).getTitle());
//
        question.setText("What requires more energy?");

        List<JokerCard> jokerList = player.getJokerCards();
        initialiseActivityImages(actList);
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

    @FXML

    private void initialiseActivityImages(List<Activity> activityList) {
       String  server = "http://localhost:8080/";

        option1Image.setImage(new Image(server + activityList.get(0).getImage_path()));
        option2Image.setImage(new Image(server + activityList.get(1).getImage_path()));
        option3Image.setImage(new Image(server + activityList.get(2).getImage_path()));
    }


    @FXML
    void exit(ActionEvent event) {
        mainCtrl.goTo("menu");
    }


}

