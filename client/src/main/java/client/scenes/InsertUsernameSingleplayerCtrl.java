package client.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class InsertUsernameSingleplayerCtrl {


    private final MainCtrl mainCtrl;
    @FXML
    private TextField username;
    @FXML
    private Label errorLabel;


    @Inject
    public InsertUsernameSingleplayerCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * Proceeds to SinglePlayer lobby screen if a username has been provided. If no username has been
     * provided, the user is prompted to try again.
     */
    public void submit() {
        String insertedUsername = username.getText();
        if (insertedUsername.length()==0) {
            errorLabel.setText("You did not enter a username, please try again.");
        } else {
            mainCtrl.goTo("singleLobby");
        }

    }

    public void returnToMenu(ActionEvent actionEvent) {
        mainCtrl.goTo("menu");
    }
}
