package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import javax.inject.Inject;
import java.util.Timer;
import java.util.TimerTask;

public class MultiplayerIntermediateScreenCtrl {
    @FXML
    private Label name1;

    @FXML
    private Label name2;

    @FXML
    private Label name3;

    @FXML
    private Label name4;

    @FXML
    private Label name5;

    @FXML
    private Label name6;

    @FXML
    private Label name7;

    @FXML
    private Label name8;

    @FXML
    private Label name9;

    @FXML
    private Label name10;

    @FXML
    private Label playerName;

    @FXML
    private Label playerScore;

    @FXML
    private Label score1;

    @FXML
    private Label score2;

    @FXML
    private Label score3;

    @FXML
    private Label score4;

    @FXML
    private Label score5;

    @FXML
    private Label score6;

    @FXML
    private Label score7;

    @FXML
    private Label score8;

    @FXML
    private Label score9;

    @FXML
    private Label score10;

    @FXML
    private Text countdown;
    private MainCtrl mainCtrl;

    @Inject
    public MultiplayerIntermediateScreenCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * Starts the countdown for the next question
     */
    public void startCountdown() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 5;
            @Override
            public void run() {
                if(i <= 0){
                    timer.cancel();
                    mainCtrl.checkGameStatus();
                }
                countdown.setText("Game Continues in " + i + " Seconds");
                i--;
            }
        }, 0, 1000);
    }
}
