package client.scenes;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class SinglePlayerLobbyCtrl {

    @FXML
    private Label tmpLabel1;
    @FXML
    private Label jokerInfoLabel;
    @FXML
    private Label jokerNumber;
//    @FXML
//    private CheckBox timeTwister;
    @FXML
    private CheckBox pointBoost;
    @FXML
    private CheckBox detective;
    @FXML
    private CheckBox questionChange;
//    @FXML
//    private CheckBox skipQuestion;
//    @FXML
//    private CheckBox flash;
//    @FXML
//    private CheckBox emergencyCall;

    private final MainCtrl mainCtrl;

    ArrayList<CheckBox> checkedJokers = new ArrayList<CheckBox>();
    List<String> checkedStringJokers ;

    @Inject
    public SinglePlayerLobbyCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        checkedStringJokers = new ArrayList<>();
    }

    @FXML
    protected void startGameButtonClick(){
        tmpLabel1.setText("Started the game!");
        mainCtrl.goTo("singleGame");
        mainCtrl.setStringJokers(checkedStringJokers);
        resetScreen();
    }

    /**
     * Resets the checkedboxes once the game starts
     */
    private void resetScreen() {
        for(int i = 0; i < checkedStringJokers.size(); i++){
            switch (checkedStringJokers.get(i)){
                case "AdditionalPointsJoker":
                    pointBoost.setSelected(false);
                    break;
                case "EliminateOptionJoker":
                    detective.setSelected(false);
                    break;
                case "QuestionChangeJoker":
                    questionChange.setSelected(false);
                    break;
            }
        }
        checkedStringJokers = new ArrayList<>();
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

//    @FXML
//    protected void addEmergencyCall(){
//        addJokerCard(emergencyCall);
//        addJokerCard("EmergencyJoker");
//    }

//    @FXML
//    protected void addTimeTwister(){
//        addJokerCard(timeTwister);
//        addJokerCard("ShortenTimeJoker");
//    }
    public void resetJokers(){
        this.checkedStringJokers = new ArrayList<>();
    }

    @FXML
    protected void addPointBoost(){
        addJokerCard(pointBoost);
        addStringJokerCard("AdditionalPointsJoker");
    }

    @FXML
    protected void addDetective(){
        addJokerCard(detective);
        addStringJokerCard("EliminateOptionJoker");
    }

    @FXML
    protected void addQuestionChange(){
        addJokerCard(questionChange);
        addStringJokerCard("QuestionChangeJoker");
    }

//    @FXML
//    protected void addSkipQuestion(){
//        addJokerCard(skipQuestion);
//        addJokerCard("SkipQuestion");
//    }
//
//    @FXML
//    protected void addFlash(){
//        addJokerCard(flash);
//        addJokerCard("Flash");
//    }

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

    /**
     * This method adds the String corresponding to the selected joker cards to the ArrayList of Strings
     * @param e String representing the selected joker card
     */
    protected void addStringJokerCard(String e){
        if(checkedStringJokers.size() < 3){
            checkedStringJokers.add(e);
            jokerNumber.setText(Integer.toString(checkedStringJokers.size()));
        }
        else if (checkedStringJokers.size() == 3){
            checkedStringJokers.remove(0);
            checkedStringJokers.add(e);
        }
    }

    @FXML
    protected void goToHelp(){
        mainCtrl.goTo("help");
    }
}
