package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class InsertUsernameSinglePlayerCtrl {
    private final MainCtrl mainCtrl;
    private final ServerUtils server;


    @Inject
    public InsertUsernameSinglePlayerCtrl(MainCtrl main) {
        this.mainCtrl=main;
        this.server = mainCtrl.getServer();
    }

    @FXML
    private Label usernameLabel;
    @FXML
    private TextField username;

    @FXML
    private TextField url;

    @FXML
    private Button submitButton;


    /**
     * This method sends the username inserted by the user to the createPlayer method in order to create a new
     * Player instance that will be passed to the playSinglePLayerGame
     */
    public void submit() {
        String insertedUsername = username.getText();
        String serverURL = url.getText();
        server.setSERVER(serverURL);
        Player player = mainCtrl.createPlayer(insertedUsername,mainCtrl.getStringJokers());
        mainCtrl.startSinglePlayerGameCountdown(player);

    }



    public void returnToLobby(){
        mainCtrl.goTo("singleLobby");
    }
}
