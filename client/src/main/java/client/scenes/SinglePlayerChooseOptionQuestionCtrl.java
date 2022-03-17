package client.scenes;

import com.google.inject.Inject;
import commons.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    @Inject
    public SinglePlayerChooseOptionQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * This method initialises all the JFX fields with attributes of the Question and Player Classes.
     * Goes to the intermediate screen after X seconds where X is the maximum allowed time.
     */
    public void initialiseMostEnergyQuestion(){
        Game currentGame = mainCtrl.getGame();
        MostEnergyQuestion q = (MostEnergyQuestion)currentGame.getQuestions().
                get(currentGame.getCurrentQuestionNumber());
        Player player = ((SinglePlayerGame)currentGame).getPlayer();
        score.setText(String.valueOf(player.getCurrentScore()));
        List<Activity> actList = q.getOtherActivities();
        actList.add(q.getActivity());
        option1.setText(actList.get(0).getTitle());
        option2.setText(actList.get(1).getTitle());
        option3.setText(actList.get(2).getTitle());
        question.setText("What requires more energy?");

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
        // go to the intermediate screen after X seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    mainCtrl.goTo("intermediateScreen");
                    System.out.println("I went to the intermediate screen");
                });
            }
        }, (int)q.getAllowedTime()*100); // change this to 1000 when deploying
    }


    // some method to automatically go to the next question


    @FXML
    void exit(ActionEvent event) {
        mainCtrl.goTo("menu");
    }


}

