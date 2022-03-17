package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.util.Timer;

import java.util.ArrayList;
import java.util.TimerTask;

public class MultiPlayerLobbyCtrl {

    @FXML
    private Label numberOfPlayersLabel;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextArea userNames;
    @FXML
    private TextArea gameStatusTextArea;

    private final MainCtrl mainCtrl;

    ArrayList<String> currentUsernames = new ArrayList<>();

    @Inject
    public MultiPlayerLobbyCtrl(MainCtrl mainCtrl){
        this.mainCtrl = mainCtrl;
    }

    /**
     * This method starts the timer for the game to start and also starts the game
     */
    @FXML
    protected void startGameButtonClick(){
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            int i = 5;
            public void run() {
                gameStatusTextArea.setText("Game Starts in\n" + i + " seconds");
                i--;

                if (i < 0) {
                    timer1.cancel();
                    gameStatusTextArea.setText("Game Starting!");
                }
            }
        }, 0, 1000);
    }

    @FXML
    protected void returnScreen(){
        mainCtrl.goTo("insertInfoMultiPlayer");
    }

    /**
     * This method will take care of registering user's names. It currently has no validation and/or sanity checks
     */
    @FXML
    protected void enterUsersName(){
        currentUsernames.add(userNameTextField.getText());
        numberOfPlayersLabel.setText(currentUsernames.size() + " Players");
        userNames.setText(MakeList(currentUsernames));
    }

    /**
     * This method will return a string-form of a list of users
     * @param currentUsernames the list of users
     * @return the string-form of the list
     */
    private String MakeList(ArrayList<String> currentUsernames) {
        String currentUsers = "";
        for(int i = 0; i < currentUsernames.size(); i++){
            currentUsers = currentUsers + currentUsernames.get(i) + "\n";
        }
        return currentUsers;
    }
}
