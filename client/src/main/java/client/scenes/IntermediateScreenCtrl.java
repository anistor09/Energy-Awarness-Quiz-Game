package client.scenes;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.util.Timer;
import java.util.TimerTask;

public class IntermediateScreenCtrl {
    @FXML
    private Label timeLeftLabel;
    @FXML
    private Label pointsLabel;      // needs to be injected by accessing the points scored by the player.

    private final MainCtrl mainCtrl;

    @Inject
    public IntermediateScreenCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * Initialises the intermediate between-questions screen with a countdown timer and the points
     * earned by the player after answering the question.
     */
    public void initialiseScene() {
        Timer timerLabel = new Timer();
        timerLabel.scheduleAtFixedRate(new TimerTask() {
            int i = 5;
            @Override
            public void run() {
                Platform.runLater(
                        () -> {
                            timeLeftLabel.setText(String.valueOf(i + 1));
                        }
                );
                i--;
                if(i < 0 ){
                    timerLabel.cancel();
                    mainCtrl.checkGameStatus();
                }
            }
        }, 0,1000);
    }
}
