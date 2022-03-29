package client.scenes;

import commons.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


import javax.inject.Inject;
import java.util.*;

public class SingleplayerInsteadOfQuestionCtrl {

    @FXML
    private Button exit;
    @FXML
    private Label jokerMessage;
    @FXML
    private Text question1Text;

    @FXML
    private Text question2Text;

    @FXML
    private Text question3Text;

    @FXML
    private Text activity1ratio;

    @FXML
    private Text activity2ratio;

    @FXML
    private Text activity3ratio;

    @FXML
    private Button joker1;

    @FXML
    private Button joker2;

    @FXML
    private Button joker3;

    @FXML
    private Button option1;

    @FXML
    private ImageView option1Image;

    @FXML
    private Button option2;

    @FXML
    private ImageView option2Image;

    @FXML
    private Button option3;

    @FXML
    private ImageView option3Image;

    @FXML
    private Label question;

    @FXML
    private Label score;

    @FXML
    private Label time;

    @FXML
    private Label questionNumber;

    private final MainCtrl mainCtrl;
    private InsteadOfQuestion questionObject;

    private IntermediateScreenCtrl intermediateScreenCtrl;
    private static int pointsGained;   //

    //TODO : set pointsGained to questionObject.getAvailablePoints() when answer validation is done.


    @Inject
    public SingleplayerInsteadOfQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * /**
     *      This method initialises all the JFX fields with
     *      attributes of the Question and Player Classes.
     */
    public void initialiseSinglePlayerInsteadOfQuestion() {
        switchButtons(false);
        Game currentGame = mainCtrl.getGame();
        InsteadOfQuestion q =
                (InsteadOfQuestion) currentGame.
                        getQuestions().
                        get(currentGame.getCurrentQuestionNumber());
        questionObject = q;
        Player player = ((SinglePlayerGame) currentGame).getPlayer();
        score.setText(String.valueOf((player.getCurrentScore())));
        Activity activity = q.getActivity();
        question.setText("Instead of " + activity.getTitle());
        ArrayList<Activity> options = q.getOptions();
        Activity correctAnswer = q.getCorrectAnswer();

        List<Activity> optionsCopy = new ArrayList<>();
        for(Activity option : options){
            optionsCopy.add(option);
        }

        String option1ratio = q.getCorrectRatio(correctAnswer) + " times";
        String option2ratio = q.getWrongRatio((options.get(1))) + " times";
        String option3ratio = q.getWrongRatio((options.get(2))) + " times";

        // In this loop we are making sure that randomly assigned wrongRatio
        // is not accidentally the correct one.
        // We are assigning it randomly until both of them are not equal to the correctRatio
        while(option2ratio.equals(q.getCorrectRatio(options.get(1))) ||
                option3ratio.equals(q.getCorrectRatio(options.get(2)))){
            option2ratio = q.getWrongRatio((options.get(1))) + " times";
            option3ratio = q.getWrongRatio((options.get(2))) + " times";
        }

        Map<String, String> optionsWithRatios = new HashMap<>();
        optionsWithRatios.put(optionsCopy.get(0).getTitle(), option1ratio);
        optionsWithRatios.put(optionsCopy.get(1).getTitle(), option2ratio);
        optionsWithRatios.put(optionsCopy.get(2).getTitle(), option3ratio);

        List<String> answers = new ArrayList<>();
        answers.add(option1ratio);
        answers.add(option2ratio);
        answers.add(option3ratio);


        Collections.shuffle(options);
//        Collections.shuffle(answers);

        question1Text.setText(String.valueOf(options.get(0).getTitle()));
        question2Text.setText(String.valueOf(options.get(1).getTitle()));
        question3Text.setText(String.valueOf(options.get(2).getTitle()));

        activity1ratio.setText(optionsWithRatios.get(options.get(0).getTitle()));
        activity2ratio.setText(optionsWithRatios.get(options.get(1).getTitle()));
        activity3ratio.setText(optionsWithRatios.get(options.get(2).getTitle()));

        setQuestionNumber("Question " + currentGame.getCurrentQuestionNumber() + "/" +
                (currentGame.getQuestions().size() - 1));

        List<JokerCard> jokerCards = player.getJokerCards();
        initialiseActivityImages(options);
        setJokers(jokerCards);
        jokerMessage.setText("");
    }

    /**
     * This method will switch the buttons on or off according to the boolean passed. True means off
     * @param onOff the boolean for which to set the setDisable property
     */
    void switchButtons(boolean onOff) {
        option1.setDisable(onOff);
        option2.setDisable(onOff);
        option3.setDisable(onOff);
        joker1.setDisable(onOff);
        joker2.setDisable(onOff);
        joker3.setDisable(onOff);
    }

    /**
     * Handles the clicks on button with option 1
     */
    public void option1Handler() {
        if(questionObject.getOptions().indexOf(questionObject.getCorrectAnswer()) == 0){
            handleCorrect();
        } else {
            handleWrong();
        }
        switchButtons(true);
    }

    /**
     * Handles the clicks on button with option 2
     */
    public void option2Handler() {
        if(questionObject.getOptions().indexOf(questionObject.getCorrectAnswer()) == 1){
            handleCorrect();
        } else {
            handleWrong();
        }
        switchButtons(true);
    }

    /**
     * Handles the clicks on button with option 3
     */
    public void option3Handler(){
        if(questionObject.getOptions().indexOf(questionObject.getCorrectAnswer()) == 2){
            handleCorrect();
        } else {
            handleWrong();
        }
        switchButtons(true);
    }

    /**
     * This method will handle when the user click the correct option. For the moment it is increasing the points of the
     * player and printing out correct
     */
    void handleCorrect() {
        // get the time left
        SinglePlayerGame spg = (SinglePlayerGame) mainCtrl.getGame();
        questionObject = (InsteadOfQuestion)spg.getQuestions().get(spg.getCurrentQuestionNumber());
        int timeAfterQuestionStart = questionObject.getAllowedTime() - MainCtrl.getTimeLeft();
        double quotient = (double)timeAfterQuestionStart / (double)questionObject.getAllowedTime();
        int points = (int) ((1 - 0.5*quotient)*questionObject.getAvailablePoints());
        Player p = ((SinglePlayerGame) mainCtrl.getGame()).getPlayer();
        p.setCurrentScore(p.getCurrentScore() + points);
        IntermediateScreenCtrl.setPointsGained(points);
        System.out.println("correct");
    }

    /**
     * This method will handle when the user clicks the wrong option. For the moment it is only printing wrong on the
     * console
     */
    void handleWrong() {
        IntermediateScreenCtrl.setPointsGained(0);
        System.out.println("wrong");
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
        if (canUseJoker(joker3.getText())) {
            jokerMessage.setText("");
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

    private void initialiseActivityImages(List<Activity> activityList) {
        String  server = "http://localhost:8080/";

        option1Image.setImage(new Image(server + activityList.get(0).getImage_path()));
        option2Image.setImage(new Image(server + activityList.get(1).getImage_path()));
        option3Image.setImage(new Image(server + activityList.get(2).getImage_path()));
    }

    @FXML
    protected void goToMenu() {
        mainCtrl.setExitedGame(true);
        mainCtrl.goTo("menu");
    }


    @FXML
    public void setTime(int i) {
        time.setText("Time Left: " + String.valueOf(i));
    }

    public int getPointsGained() {
        return pointsGained;
    }

    public void setPointsGained(int pointsGained) {
        this.pointsGained = pointsGained;
    }
    public void setQuestionNumber(String i) {
        questionNumber.setText(i);
    }

}
