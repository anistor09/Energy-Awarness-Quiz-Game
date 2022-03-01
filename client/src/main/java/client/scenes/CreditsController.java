package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.Timer;

import java.util.ArrayList;
import java.util.TimerTask;

public class CreditsController {


    @FXML
    private Label returnLabel;

    @FXML
    protected void returnMenu(){
        returnLabel.setText("Returning!");
    }

}
