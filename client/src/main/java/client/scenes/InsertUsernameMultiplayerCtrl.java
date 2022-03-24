package client.scenes;

import client.utils.ServerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class InsertUsernameMultiplayerCtrl {

    private final MainCtrl mainCtrl;


    @Inject
    public InsertUsernameMultiplayerCtrl(MainCtrl main) {
        this.mainCtrl=main;
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
     * This method sends the username and url in order to connect to the server and add the player to the right lobby
     * but it is not fully implemented yet.
     */
    public void submit() {
//                myLabel.setText("signed up");

        String insertedUsername = username.getText();
        String insertedUrl = url.getText();
        ServerUtils.setSERVER(insertedUrl);
        System.out.println(insertedUrl);
        System.out.println(insertedUsername);
        mainCtrl.goTo("multiLobby");
    }
    public void returnToMenu() {
        mainCtrl.goTo("menu");
    }
}
