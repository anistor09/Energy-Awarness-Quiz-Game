package client.scenes;

import com.google.inject.Inject;
import commons.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.util.List;

public class SinglePlayerOpenQuestionCtrl {

    @FXML
    private Button exit;
    @FXML
    private Label jokerMessage;

    @FXML
    private ImageView image;

    @FXML
    private ImageView joker;

    @FXML
    private Button joker1;

    @FXML
    private Button joker2;

    @FXML
    private Button joker3;

    @FXML
    private Label question;

    @FXML
    private Label score;
    @FXML
    private Text questionText;

    @FXML
    private Label time;

    @FXML
    private Label questionNumber;

    @FXML
    private TextField userAnswer;
    private final MainCtrl mainCtrl;

    private GuessQuestion questionObject;

    private static int pointsGained = 0;

    @Inject
    public SinglePlayerOpenQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    @FXML
    void exit(ActionEvent event) {
        mainCtrl.setExitedGame(true);
        mainCtrl.goTo("menu");
    }


    /**
     * This method initialises all the JFX fields with attributes of the Question and Player Classes.
     * Goes to the intermediate screen after X seconds where X is the maximum allowed time.
     */
    public void initialiseSinglePlayerOpenQuestion() {
        resetScreen();
        switchButtons(false);
        Game currentGame = mainCtrl.getGame();
        GuessQuestion q = (GuessQuestion)currentGame.getQuestions().
                get(currentGame.getCurrentQuestionNumber());
        questionObject = q;
        Player player = ((SinglePlayerGame)currentGame).getPlayer();
        score.setText(String.valueOf(player.getCurrentScore()));
        Activity act = q.getActivity();
        question.setText("How much energy does it take?");
        questionText.setText(act.getTitle());
        jokerMessage.setText("");
        initialiseActivityImage(act);

        setQuestionNumber("Question " + currentGame.getCurrentQuestionNumber() + "/" +
                (currentGame.getQuestions().size() - 1));

        List<JokerCard> jokerList = player.getJokerCards();
        this.setJokers(jokerList);

    }

    public void resetScreen(){
        userAnswer.setText("");
    }

    /**
     * This method maps the player's jokers to their corresponding buttons
     * @param jokerList List of JokerCard instances representing the player's jokers
     */
    public void setJokers(List<JokerCard> jokerList){
       Button[] buttonArray ={ joker1,joker2,joker3};

       for(int i=0;i<buttonArray.length;i++){
           Button current = buttonArray[i];
           if(i<=jokerList.size()-1){

               current.setText(jokerList.get(i).getName());
           }
           else{
               current.setText("Unavailable Joker");
               current.setDisable(true);
           }

       }
    }
    /**
     * This method initialises the Image view with the corresponding image of the activity
     * @param act Instance of Activity
     */
    private void initialiseActivityImage(Activity act) {
        String  server = "http://localhost:8080/";
        Image img = new Image(server + act.getImage_path());

        image.setImage(img);
    }

    public void setTime(int i) {
        time.setText("Time Left: " + String.valueOf(i));
    }

    /**
     * This is the onAction method for the Label. When the user hits enter this will be called. It will either
     * calculate the number of points in case it is an integer or clear the field in case an exception is thrown.
     * All the buttons are disabled after the call
     */
    public void changeGuess() {
        try {
            long guess = Long.parseLong(userAnswer.getCharacters().toString());
            switchButtons(true);
            int points = questionObject.calculatePoints(guess);
            Player p = ((SinglePlayerGame) mainCtrl.getGame()).getPlayer();
            p.setCurrentScore(p.getCurrentScore() + points);
            System.out.println(guess);
            System.out.println("You earned " + points);
            this.setPointsGained(points);
        } catch (Exception e) {
            userAnswer.clear();
            System.out.println("Not a number");
        }
    }

    /**
     * This method will switch the buttons on or off according to the boolean passed. True means off
     * @param onOff the boolean for which to set the setDisable property
     */

    void switchButtons(boolean onOff) {
        userAnswer.setDisable(onOff);
        joker1.setDisable(onOff);
        joker2.setDisable(onOff);
        joker3.setDisable(onOff);
    }

    public int getPointsGained() {
        return pointsGained;
    }

    public void setPointsGained(int pointsGained) {
        this.pointsGained = pointsGained;
    }
    @FXML
    void handleJokerButton1() {
        if(canUseJoker(joker1.getText())) {
            jokerMessage.setText("");
            mainCtrl.setUsedJoker(joker1.getText());
            mainCtrl.handleJoker();
        }
        else{
            jokerMessage.setText("This joker cannot be used in this type of question!");
        }
    }
    @FXML
    void handleJokerButton2() {
        if(canUseJoker(joker2.getText())) {
            jokerMessage.setText("");
            mainCtrl.setUsedJoker(joker2.getText());
            mainCtrl.handleJoker();
        }
        else{
            jokerMessage.setText("This joker cannot be used in this type of question!");
        }
    }
    @FXML
    void handleJokerButton3() {
        jokerMessage.setText("");
        if (canUseJoker(joker3.getText())) {
            mainCtrl.setUsedJoker(joker3.getText());
            mainCtrl.handleJoker();
        }
        else{
            jokerMessage.setText("This joker cannot be used in this type of question!");
        }
    }
    public boolean canUseJoker(String name){
        if(name.equals("EliminateOptionJoker"))
            return false;
        return true;
    }

    public void setQuestionNumber(String i) {
        questionNumber.setText(i);
    }

}
