package client.scenes;

import commons.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javax.inject.Inject;
import java.util.Timer;

import java.util.ArrayList;
import java.util.TimerTask;

public class MultiPlayerLobbyCtrl {

    @FXML
    private Label numberOfPlayersLabel;
    @FXML
    private TextArea userNames;
    @FXML
    private TextArea gameStatusTextArea;

    private final MainCtrl mainCtrl;

    ArrayList<Player> players = new ArrayList<>();

    @Inject
    public MultiPlayerLobbyCtrl(MainCtrl mainCtrl){
        this.mainCtrl = mainCtrl;
    }

    /**
     * This method starts the timer for the game to start and also starts the game
     */
    @FXML
    protected void startGameButtonClick(){
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            int i = 5;
            public void run() {
                gameStatusTextArea.setText("Game Starts in\n" + i + " seconds");

                if (i == 0) {
                    gameStatusTextArea.setText("Game Starting!");
                }

                if(i < 0){
                    timer1.cancel();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            mainCtrl.playMultiPLayerGame();
                        }
                    });
                }

                i--;
            }
        }, 0, 1000);
    }

    @FXML
    protected void returnScreen(){
        mainCtrl.goTo("insertInfoMultiPlayer");
    }

    /**
     * This method will return a string-form of a list of users
     * @param currentUsernames the list of users
     * @return the string-form of the list
     */
    private String MakeList(ArrayList<String> currentUsernames) {
        String currentUsers = "";
        for(int i = 0; i < players.size(); i++){
            currentUsers = currentUsers + players.get(i).getUsername() + "\n";
        }
        return currentUsers;
    }
}
