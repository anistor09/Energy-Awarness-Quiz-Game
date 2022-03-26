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

    private final SingleplayerInsteadOfQuestionCtrl insteadOfQuestionCtrl;
    private final SinglePlayerChooseOptionQuestionCtrl mostEnergyQuestionCtrl;
    private final SinglePlayerGameCtrl multipleChoiceQuestionCtrl;
    private final SinglePlayerOpenQuestionCtrl guessQuestionCtrl;

    @Inject
    public IntermediateScreenCtrl(MainCtrl mainCtrl, SingleplayerInsteadOfQuestionCtrl insteadOfQuestionCtrl,
                                  SinglePlayerChooseOptionQuestionCtrl mostEnergyQuestionCtrl,
                                  SinglePlayerGameCtrl multipleChoiceQuestionCtrl,
                                  SinglePlayerOpenQuestionCtrl guessQuestionCtrl) {
        this.mainCtrl = mainCtrl;
        this.insteadOfQuestionCtrl = insteadOfQuestionCtrl;
        this.mostEnergyQuestionCtrl = mostEnergyQuestionCtrl;
        this.multipleChoiceQuestionCtrl = multipleChoiceQuestionCtrl;
        this.guessQuestionCtrl = guessQuestionCtrl;
    }

    /**
     * Initialises the intermediate between-questions screen with a countdown timer and the points
     * earned by the player after answering the question.
     */
    public void initialiseScene() {
        // go to the intermediate screen after X seconds

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

    // set the pointsLabel to the appropriate points depending on the type of question.

    /**
     * Sets the points label to the appropriate number depending on the preceding question.
     * @param ctrl Controller for the preceding question.
     */
    public void setPointsLabel(Object ctrl) {
        String className = mainCtrl.getClassName(ctrl.getClass().getName());
        switch (className) {
            case "SinglePlayerGameCtrl":
                this.pointsLabel.setText(String.valueOf(((SinglePlayerGameCtrl)ctrl).getPointsGained()));
                break;

            case "SinglePlayerChooseOptionQuestionCtrl":
                this.pointsLabel.setText(String.valueOf(((SinglePlayerChooseOptionQuestionCtrl)ctrl).
                        getPointsGained()));
                break;

            case "SinglePlayerOpenQuestionCtrl":
                this.pointsLabel.setText(String.valueOf(((SinglePlayerOpenQuestionCtrl)ctrl).getPointsGained()));
                break;

            case "SingleplayerInsteadOfQuestionCtrl":
                this.pointsLabel.setText(String.valueOf(((SingleplayerInsteadOfQuestionCtrl)ctrl).getPointsGained()));
                break;
            default:
                System.out.println("None found");
                break;
        }
    }
}
