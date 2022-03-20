package client.scenes;

import commons.Player;
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
     * This method sends the username inserted by the user to the createPlayer method in order to create a new
     * Player instance that will be passed to the playSinglePLayerGame
     */
    public void submit() {
//

        String insertedUsername = username.getText();
        Player player = mainCtrl.createPlayer(insertedUsername,mainCtrl.getStringJokers());
        mainCtrl.playSinglePLayerGame(player);

    }
    public void returnToLobby(){
        mainCtrl.goTo("singleLobby");
    }
}
