package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class InsertUsernameMultiplayerCtrl {

    private final MainCtrl mainCtrl;
    private final ServerUtils server;
    private final MultiPlayerLobbyCtrl lobby;
    List<String> listOfPlayer;
    private ServerUtils serverUtils;


    @Inject
    public InsertUsernameMultiplayerCtrl(MainCtrl main,  MultiPlayerLobbyCtrl lobby) {
        this.mainCtrl=main;
        this.server = main.getServer();
        this.lobby = lobby;
    }

    @FXML
    private Label usernameLabel;
    @FXML
    private TextField username;
    @FXML
    private TextField url;
    @FXML
    private Button submitButton;
    @FXML
    private Label alert;


    /**
     * This method sends the username and url in order to connect to the server and add the player to the right lobby
     * but it is not fully implemented yet.
     */
    public void submit() {
        String insertedUsername = username.getText();
        if(insertedUsername.length() > 13) {
            alert.setText("Username too long!");
            return;
        }
        if (insertedUsername.length() == 0|| insertedUsername.contains(" ")) {
            alert.setText("Invalid username");
            return;
        }
        listOfPlayer = server.getCurrentMultiGamePlayers().stream().
                    map(Player::getUsername).collect(Collectors.toList());
        if(listOfPlayer.contains(insertedUsername)) {
            alert.setText("Username already exists!");
            return;
        }
        Player thisPlayer = new Player(insertedUsername, 0);
        server.sendPlayer(thisPlayer);
        mainCtrl.setLocalPlayer(thisPlayer);
        String insertedUrl = url.getText();
        System.out.println(insertedUrl);
        if(serverUtils.testConnection(insertedUrl)){
            server.setSERVER(insertedUrl);
        }
        else{
            System.out.println("Provided server url is wrong!");
            mainCtrl.goTo("error");
        }
        System.out.println(insertedUsername);
        mainCtrl.goTo("multiLobby");
    }

    /**
     * This method server to return to the menu
     */
    public void returnToMenu() {
        mainCtrl.goTo("menu");
    }
}
