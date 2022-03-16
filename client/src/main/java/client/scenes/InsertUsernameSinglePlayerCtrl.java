package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class InsertUsernameSinglePlayerCtrl {
    private final MainCtrl mainCtrl;


    @Inject
    public InsertUsernameSinglePlayerCtrl(MainCtrl main) {
        this.mainCtrl=main;
    }

    @FXML
    private Label usernameLabel;
    @FXML
    private TextField username;

    @FXML
    private Button submitButton;


    /**
     * This method sends the username in order to connect to the server and add the player to the game
     * but it is not fully implemented yet.
     */
    public void submit() {
//

        String insertedUsername = username.getText();
        mainCtrl.playSinglePLayerGame(insertedUsername);

    }
}
