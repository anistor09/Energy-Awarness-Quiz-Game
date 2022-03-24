package client.scenes;

import client.utils.ServerUtils;
import commons.MultiPlayerGame;
import commons.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class MultiPlayerLobbyCtrl {

    @FXML
    private Label alert;
    @FXML
    private Label numberOfPlayersLabel;
    @FXML
    private TextArea userNames;

    private final MainCtrl mainCtrl;
    private final ServerUtils server;

    List<String> playerUsernames;
    private MultiPlayerGame game;
    private Player thisPlayer;

    @Inject
    public MultiPlayerLobbyCtrl(MainCtrl mainCtrl, ServerUtils server){
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

//    /**
//     * This method starts the timer for the game to start and also starts the game
//     */
//    @FXML
//    protected void startGameButtonClick(){
//        Timer timer1 = new Timer();
//        timer1.scheduleAtFixedRate(new TimerTask() {
//            int i = 5;
//            public void run() {
//                gameStatusTextArea.setText("Game Starts in\n" + i + " seconds");
//
//                if (i == 0) {
//                    gameStatusTextArea.setText("Game Starting!");
//                }
//
//                if(i < 0){
//                    timer1.cancel();
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run(){
//                            mainCtrl.goTo("multiGame");
//                        }
//                    });
//                }
//
//                i--;
//            }
//        }, 0, 1000);
//    }

    @FXML
    protected void returnScreen(){
        server.deletePlayer(thisPlayer);
        mainCtrl.goTo("insertInfoMultiPlayer");
    }

    public void initialize() {
        game = server.getCurrentMultiplayerGame();
        playerUsernames = game.getPlayers().stream().map(Player::getUsername).collect(Collectors.toList());
        server.registerForNewPlayers("/topic/updateLobby", p -> {
            game.getPlayers().add(p);
            playerUsernames.add(p.getUsername());
            Platform.runLater(() -> refresh());
        });
        server.registerForDeletedPlayers("/topic/deletePlayer", p -> {
            game.getPlayers().remove(p);
            playerUsernames.remove(p.getUsername());
            Platform.runLater(() -> refresh());
        });
    }

    public void refresh() {
        numberOfPlayersLabel.setText(playerUsernames.size() + "Players");
        userNames.setText(MakeList(playerUsernames));
    }

    /**
     * This method will return a string-form of a list of users
     * @param currentUsernames the list of users
     * @return the string-form of the list
     */
    private String MakeList(List<String> currentUsernames) {
        String currentUsers = "";
        for(int i = 0; i < currentUsernames.size(); i++){
            currentUsers = currentUsers + currentUsernames.get(i) + "\n";
        }
        return currentUsers;
    }

    public void setThisPlayer(Player thisPlayer) {
        this.thisPlayer = thisPlayer;
    }
}
