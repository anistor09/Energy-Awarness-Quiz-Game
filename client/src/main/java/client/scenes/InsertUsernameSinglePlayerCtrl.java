package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

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

    @FXML
    private BorderPane root;


    /**
     * This method sends the username inserted by the user to the createPlayer method in order to create a new
     * Player instance that will be passed to the playSinglePLayerGame
     */
    public void submit() {
        String insertedUsername = username.getText();
        String serverURL = url.getText();
        if(server.testConnection(serverURL)){
            server.setSERVER(serverURL);
        }
        else{
            System.out.println("Provided server url is wrong!");
            mainCtrl.goTo("error");
        }
        Player player = mainCtrl.createPlayer(insertedUsername,mainCtrl.getStringJokers());
        mainCtrl.startSinglePlayerGameCountdown(player);

    }

    /**
     * This method prepares the scene in order to respond to the input of the user
     */
    public void prepare() {
        root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                ev.consume();
                submit();
            }
        });
    }



    public void returnToLobby(){
        mainCtrl.goTo("singleLobby");
    }
}
