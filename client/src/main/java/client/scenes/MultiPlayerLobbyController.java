package client.scenes;

import client.Main;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.util.Timer;

import java.util.ArrayList;
import java.util.TimerTask;

public class MultiPlayerLobbyController {

    @FXML
    private Label helpLabel;
    @FXML
    private Label numberOfPlayersLabel;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextArea userNames;
    @FXML
    private TextArea gameStatusTextArea;

    private final MainCtrl mainCtrl;
    ArrayList<String> currentUsernames = new ArrayList<String>();

    @Inject
    public MultiPlayerLobbyController(MainCtrl mainCtrl){
        this.mainCtrl = mainCtrl;
    }

    @FXML
    protected void startGameButtonClick(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 15;
            public void run() {
                gameStatusTextArea.setText("Game Starts in\n" + i + " seconds");
                i--;

                if (i < 0) {
                    timer.cancel();
                    gameStatusTextArea.setText("Game Starting!");
                }
            }
        }, 0, 1000);
    }

    @FXML
    protected void goToHelpScreen(){
        helpLabel.setText("You got help!");
    }

    @FXML
    protected void returnScreen(){
        mainCtrl.Return("menu");
    }

    @FXML
    protected void enterUsersName(){
        currentUsernames.add(userNameTextField.getText());
        numberOfPlayersLabel.setText(currentUsernames.size() + " Players");
        userNames.setText(MakeList(currentUsernames));
    }

    private String MakeList(ArrayList<String> currentUsernames) {
        String currentUsers = "";
        for(int i = 0; i < currentUsernames.size(); i++){
            currentUsers = currentUsers + currentUsernames.get(i) + "\n";
        }
        return currentUsers;
    }
}
