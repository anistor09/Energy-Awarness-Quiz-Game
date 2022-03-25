package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

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

    public void startCountdown() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 5;
            @Override
            public void run() {
                if(i <= 0){
                    timer.cancel();
                    //TODO METHOD THAT GOES TO THE NEXT QUESTION
                }
                countdown.setText("Game Continues in " + i + " Seconds");
                i--;
            }
        }, 0, 1000);
    }
}
