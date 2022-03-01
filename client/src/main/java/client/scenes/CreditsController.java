package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class CreditsController {


    @FXML
    private Label returnLabel;

    @FXML
    protected void returnMenu(){
        returnLabel.setText("Returning!");
    }

}
