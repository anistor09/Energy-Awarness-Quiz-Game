package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class SinglePlayerLobbyController {

    @FXML
    private Label tmpLabel1;
    @FXML
    private Label helpLabel;
    @FXML
    private Label returnLabel;
    @FXML
    private Label jokerInfoLabel;
    @FXML
    private Label jokerNumber;
    @FXML
    private CheckBox timeTwister;
    @FXML
    private CheckBox pointBoost;
    @FXML
    private CheckBox detective;
    @FXML
    private CheckBox questionChange;
    @FXML
    private CheckBox skipQuestion;
    @FXML
    private CheckBox flash;
    @FXML
    private CheckBox emergencyCall;

    ArrayList<CheckBox> checkedJokers = new ArrayList<CheckBox>();

    @FXML
    protected void startGameButtonClick(){
        tmpLabel1.setText("Started the game!");
    }

    @FXML
    protected void goToHelpScreen(){
        helpLabel.setText("You got help!");
    }

    @FXML
    protected void returnScreen(){
        returnLabel.setText("Returning!");
    }

    @FXML
    protected void displayJokerInfo(){
        jokerInfoLabel.setText("Joker info is being displayed!");
    }

    @FXML
    protected void stopJokerInfo(){
        jokerInfoLabel.setText("Joker Info was not pressed");
    }

    @FXML
    protected void addEmergencyCall(){
        addJokerCard(emergencyCall);
    }

    @FXML
    protected void addTimeTwister(){
        addJokerCard(timeTwister);
    }

    @FXML
    protected void addPointBoost(){
        addJokerCard(pointBoost);
    }

    @FXML
    protected void addDetective(){
        addJokerCard(detective);
    }

    @FXML
    protected void addQuestionChange(){
        addJokerCard(questionChange);
    }

    @FXML
    protected void addSkipQuestion(){
        addJokerCard(skipQuestion);
    }

    @FXML
    protected void addFlash(){
        addJokerCard(flash);
    }

    protected void addJokerCard(CheckBox e){
        if(checkedJokers.size() < 3){
            checkedJokers.add(e);
            jokerNumber.setText(Integer.toString(checkedJokers.size()));
        }
        if(checkedJokers.size() == 3){
            checkedJokers.remove(0);
            checkedJokers.add(e);
        }
    }
}
