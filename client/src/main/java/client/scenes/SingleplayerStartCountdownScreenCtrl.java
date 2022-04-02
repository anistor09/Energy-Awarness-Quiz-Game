package client.scenes;

import commons.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.util.Timer;
import java.util.TimerTask;

public class SingleplayerStartCountdownScreenCtrl {

    @FXML
    private Label timeLeft;

    private final MainCtrl mainCtrl;

    @Inject
    public SingleplayerStartCountdownScreenCtrl(MainCtrl main) {
        this.mainCtrl=main;
    }

    /**
     * This methods starts the timer for the pregame countdown
     * @param player This is the object player that is playing the singleplayer game
     */
    public void startCountdown(Player player){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 5;

            @Override
            public void run() {
                if(i < 0){
                    timer.cancel();
                    Platform.runLater(() -> timeLeft.setText("Starting Game!"));
                    mainCtrl.playSinglePLayerGame(player);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timeLeft.setText((i + 1) + " Seconds!");
                    }
                });
                i--;
            }
        },0,1000);
    }
}
