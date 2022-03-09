package client.scenes;

import com.google.inject.Inject;
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

    private final MainCtrl mainCtrl;

    ArrayList<CheckBox> checkedJokers = new ArrayList<CheckBox>();

    @Inject
    public SinglePlayerLobbyController(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    @FXML
    protected void startGameButtonClick(){
        tmpLabel1.setText("Started the game!");
        mainCtrl.goTo("singleGame");
    }

    @FXML
    protected void goToHelpScreen(){
        helpLabel.setText("You got help!");
    }

    @FXML
    protected void returnScreen(){
        mainCtrl.goTo("menu");
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

    /**
     * This method adds the selected joker cards an ArrayList
     * @param e is the checkbox that was selected
     */
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
