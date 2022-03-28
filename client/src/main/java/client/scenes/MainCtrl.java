/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package client.scenes;

import client.utils.ServerUtils;
import commons.*;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.inject.Inject;
import java.util.*;


public class MainCtrl{


    private ServerUtils serverUtils;

    private Stage primaryStage;

    private MenuCtrl menuCtrl;
    private Scene menu;

    private SinglePlayerLobbyCtrl singlePlayerLobbyCtrl;
    private Scene singlePlayerLobby;

    private MultiPlayerLobbyCtrl multiPlayerLobbyCtrl;
    private Scene multiPlayerLobby;

    private SinglePlayerMultipleChoiceQuestionCtrl singlePlayerGameCtrl;
    private Scene singlePlayerGame;

    private MultiPlayerMultipleChoiceQuestionCtrl multiPlayerGameCtrl;
    private Scene multiPlayerGame;

    private CreditsCtrl creditsCtrl;
    private Scene credits;

    private MultiPlayerChooseOptionQuestionCtrl multiPlayerChooseOptionQuestionCtrl;
    private Scene multiPlayerChooseOptionQuestion;

    private SinglePlayerChooseOptionQuestionCtrl singlePlayerChooseOptionQuestionCtrl;
    private Scene singlePlayerChooseOptionQuestion;

    private MultiPlayerGuessQuestionCtrl multiPlayerOpenQuestionCtrl;
    private Scene multiPlayerOpenQuestion;

    private SinglePlayerGuessQuestionCtrl singlePlayerOpenQuestionCtrl;
    private Scene singlePlayerOpenQuestion;

    private InsertUsernameMultiplayerCtrl multiplayerInsertInfoCtrl;
    private Scene  multiplayerInsertInfo;

    private HelpCtrl helpCtrl;
    private Scene help;

    private InsertUsernameSinglePlayerCtrl singleplayerInsertInfoCtrl;
    private Scene singleplayerInsertInfo;

    private AdminPanelCtrl adminPanelCtrl;
    private Scene admin;

    private EditActivityCtrl editActivityCtrl;
    private Scene editActivity;

    private SinglePlayerLeaderboardCtrl singlePlayerLeaderboardCtrl;
    private Scene singlePlayerLeaderboard;

    private SingleplayerInsteadOfQuestionCtrl singleplayerInsteadOfQuestionCtrl;
    private Scene singleplayerInsteadOfQuestion;

    private IntermediateScreenCtrl intermediateScreenCtrl;
    private Scene intermediateScreen;

    private MultiplayerInsteadOfQuestionCtrl multiplayerInsteadOfQuestionCtrl;
    private Scene multiplayerInsteadOfQuestion;

    private SingleplayerStartCountdownScreenCtrl singleplayerStartCountdownScreenCtrl;
    private Scene singlePlayerStartCountdownScreen;

    private MultiplayerIntermediateScreenCtrl multiplayerIntermediateScreenCtrl;
    private Scene multiPlayerIntermediateScreen;

    private Game game; // An instance of Game class representing the ongoing game
    private List<String> jokersStringList; // A list of Strings representing the names of the Jokers
                                            // that the player chose to use

    private String usedJoker;
    boolean exitedGame;
    private Player localPlayer;

    private static int timeLeft;
    @Inject
    public MainCtrl(ServerUtils serverUtils) {
        this.serverUtils = serverUtils;
    }

    //test
//    List<Player> test = new ArrayList<>();


    /**
     * This method will take care of initializing all scenes present in the application and starting the app with the
     * menu
     * @param primaryStage the stage on which the app will be displayed
     * @param menuPair the pair containing the menu controller and its fxml file "Parent"
     * @param singlePlayerLobbyControllerParentPair the pair containing the singlePlayerLobby controller and its fxml
 * file "Parent"
     * @param multiPlayerLobbyControllerParentPair the pair containing the multiPlayerLobby controller and its fxml file
* "Parent"
     * @param creditsControllerParentPair the pair containing the credits controller and its fxml file "Parent"
     * @param singlePlayerGamePair the pair containing the singlePlayerGame controller and its fxml file "Parent"
     * @param multiPlayerGamePair the pair containing the multiPlayer controller and its fxml file "Parent"
     * @param multiPlayerChooseOptionQuestionControllerParentPair
     * @param singlePlayerChooseOptionQuestionControllerParentPair
     * @param multiPlayerOpenQuestionControllerParentPair
     * @param singlePlayerOpenQuestionControllerParentPair
     */
    public void initialize(Stage primaryStage, Pair<MenuCtrl, Parent> menuPair, Pair<SinglePlayerLobbyCtrl,
            Parent> singlePlayerLobbyControllerParentPair, Pair<MultiPlayerLobbyCtrl,
            Parent> multiPlayerLobbyControllerParentPair, Pair<CreditsCtrl, Parent> creditsControllerParentPair,
                           Pair<SinglePlayerMultipleChoiceQuestionCtrl, Parent> singlePlayerGamePair, Pair<MultiPlayerMultipleChoiceQuestionCtrl,
            Parent> multiPlayerGamePair,
                           Pair<MultiPlayerChooseOptionQuestionCtrl, Parent>
                                   multiPlayerChooseOptionQuestionControllerParentPair,
                           Pair<SinglePlayerChooseOptionQuestionCtrl, Parent>
                                   singlePlayerChooseOptionQuestionControllerParentPair,
                           Pair<MultiPlayerGuessQuestionCtrl, Parent> multiPlayerOpenQuestionControllerParentPair,
                           Pair<SinglePlayerGuessQuestionCtrl, Parent>
                                   singlePlayerOpenQuestionControllerParentPair,
                           Pair<InsertUsernameMultiplayerCtrl, Parent> insertInfoMultiplayer,
                           Pair<HelpCtrl, Parent> helpCtrlParentPair,
                           Pair<InsertUsernameSinglePlayerCtrl, Parent> insertInfoSingleplayer,
                           Pair<SinglePlayerLeaderboardCtrl, Parent> singlePlayerLeaderboardCtrlParentPair,
                           Pair<SingleplayerInsteadOfQuestionCtrl, Parent>
                                   singleplayerInsteadOfQuestionCtrlParentPair,
                           Pair<MultiplayerInsteadOfQuestionCtrl, Parent>
                                   multiPlayerInsteadOfQuestionCtrlParentPair,
                           Pair<AdminPanelCtrl, Parent> adminPanel, Pair<EditActivityCtrl, Parent> editActivity,
                           Pair<IntermediateScreenCtrl, Parent> intermediateScreenCtrlParentPair,
                           Pair<SingleplayerStartCountdownScreenCtrl,
                                   Parent> singleplayerStartCountdownScreenCtrlParentPair,
                           Pair<MultiplayerIntermediateScreenCtrl, Parent> multiplayerIntermediateScreenCtrlParentPair)
                            {


        this.primaryStage = primaryStage;
        this.menuCtrl = menuPair.getKey();
        this.menu = new Scene(menuPair.getValue());

        this.singlePlayerLobbyCtrl = singlePlayerLobbyControllerParentPair.getKey();
        this.singlePlayerLobby = new Scene(singlePlayerLobbyControllerParentPair.getValue());
        this.multiPlayerLobbyCtrl = multiPlayerLobbyControllerParentPair.getKey();
        this.multiPlayerLobby = new Scene(multiPlayerLobbyControllerParentPair.getValue());
        this.singlePlayerGameCtrl = singlePlayerGamePair.getKey();
        this.singlePlayerGame = new Scene(singlePlayerGamePair.getValue());
        this.multiPlayerGameCtrl = multiPlayerGamePair.getKey();
        this.multiPlayerGame = new Scene(multiPlayerGamePair.getValue());
        this.creditsCtrl = creditsControllerParentPair.getKey();
        this.credits = new Scene(creditsControllerParentPair.getValue());
        this.multiPlayerChooseOptionQuestionCtrl = multiPlayerChooseOptionQuestionControllerParentPair.getKey();
        this.multiPlayerChooseOptionQuestion = new
                Scene(multiPlayerChooseOptionQuestionControllerParentPair.getValue());
        this.singlePlayerChooseOptionQuestionCtrl = singlePlayerChooseOptionQuestionControllerParentPair.getKey();
        this.singlePlayerChooseOptionQuestion = new
                Scene(singlePlayerChooseOptionQuestionControllerParentPair.getValue());
        this.multiPlayerOpenQuestionCtrl = multiPlayerOpenQuestionControllerParentPair.getKey();
        this.multiPlayerOpenQuestion = new Scene(multiPlayerOpenQuestionControllerParentPair.getValue());
        this.singlePlayerOpenQuestionCtrl = singlePlayerOpenQuestionControllerParentPair.getKey();
        this.singlePlayerOpenQuestion = new Scene(singlePlayerOpenQuestionControllerParentPair.getValue());
        this.multiplayerInsertInfoCtrl =insertInfoMultiplayer.getKey();
        this.multiplayerInsertInfo = new Scene(insertInfoMultiplayer.getValue());
        this.helpCtrl = helpCtrlParentPair.getKey();
        this.help = new Scene(helpCtrlParentPair.getValue());
        this.singleplayerInsertInfoCtrl =insertInfoSingleplayer.getKey();
        this.singleplayerInsertInfo = new Scene(insertInfoSingleplayer.getValue());
        this.adminPanelCtrl = adminPanel.getKey();
        this.admin = new Scene(adminPanel.getValue());
        this.editActivityCtrl = editActivity.getKey();
        this.editActivity = new Scene(editActivity.getValue());
        this.singleplayerInsteadOfQuestionCtrl = singleplayerInsteadOfQuestionCtrlParentPair.getKey();
        this.singleplayerInsteadOfQuestion = new Scene(singleplayerInsteadOfQuestionCtrlParentPair.getValue());
        this.multiplayerInsteadOfQuestionCtrl = multiPlayerInsteadOfQuestionCtrlParentPair.getKey();
        this.multiplayerInsteadOfQuestion = new Scene(multiPlayerInsteadOfQuestionCtrlParentPair.getValue());
        this.singlePlayerLeaderboardCtrl = singlePlayerLeaderboardCtrlParentPair.getKey();
        this.singlePlayerLeaderboard = new Scene(singlePlayerLeaderboardCtrlParentPair.getValue());
        this.intermediateScreenCtrl = intermediateScreenCtrlParentPair.getKey();
        this.intermediateScreen = new Scene(intermediateScreenCtrlParentPair.getValue());
        this.singleplayerStartCountdownScreenCtrl = singleplayerStartCountdownScreenCtrlParentPair.getKey();
        this.singlePlayerStartCountdownScreen = new Scene(singleplayerStartCountdownScreenCtrlParentPair.getValue());
        this.multiplayerIntermediateScreenCtrl = multiplayerIntermediateScreenCtrlParentPair.getKey();
        this.multiPlayerIntermediateScreen = new Scene(multiplayerIntermediateScreenCtrlParentPair.getValue());


        this.exitedGame = false;

        this.menu.getStylesheets().add("@../../stylesheets/menu_stylesheet.css");
        this.intermediateScreen.getStylesheets().add("@../../stylesheets/singleplayer_game.css");
//        this.credits.getStylesheets().add("@../../stylesheets/menu_stylesheet.css");
//        this.singlePlayerLobby.getStylesheets().add("@../../stylesheets/menu_stylesheet.css");



        this.singleplayerInsertInfo.getStylesheets().add("@../../stylesheets/menu_stylesheet.css");
        this.singlePlayerGame.getStylesheets().add("@../../stylesheets/singleplayer_game.css");
        this.singlePlayerOpenQuestion.getStylesheets().add("@../../stylesheets/singleplayer_game.css");
        this.singlePlayerChooseOptionQuestion.getStylesheets().add("@../../stylesheets/singleplayer_game.css");
        this.singleplayerInsteadOfQuestion.getStylesheets().add("@../../stylesheets/singleplayer_game.css");
        this.singlePlayerLeaderboard.getStylesheets().add("@../../stylesheets/menu_stylesheet.css");
        this.singlePlayerStartCountdownScreen.getStylesheets().add("@../../stylesheets/menu_stylesheet.css");


        primaryStage.setTitle("Quizzz");
        goTo("menu");
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            multiPlayerLobbyCtrl.tearDown();
        }); // this is to delete the player from the game in case he was in one

        //test
//        this.serverUtils.registerForNewPlayers("/topic/updateLobby", p -> {
//            test.add(p);
//            System.out.println(p.getUsername());
//        });
    }

    public static int getTimeLeft() {
        return timeLeft;
    }

    public static void setTimeLeft(int timeLeft) {
        MainCtrl.timeLeft = timeLeft;
    }

    /**
     * The method includes the logic of the game. We will retrieve a game object from the server that will contain
     * a player attribute with the given username. In this method we will iterate through all the questions,
     * by selecting the current question from the game attribute currentQuestionNumber in the game
     * and set the correct scene for each of them
     * @param player Instance of Player representing the username inserted by the user
     */
    public void playSinglePLayerGame(Player player){
        game =serverUtils.createSinglePlayerGame(player);
        goToNextSingleplayerQuestion();

        //test
//        this.serverUtils.sendPlayer(new Player("test", 400));
    }

        
    /**
     * This is a timer that works in the background and switches to the next question
     */
    public void singleplayerInGameTimer(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = game.getQuestions().get(game.getCurrentQuestionNumber()).getAllowedTime();
            @Override
            public void run() {
                if(exitedGame){
                    timer.cancel();
                    setExitedGame(false);
                }
                if (i <= 0) {
                    timer.cancel();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            int currentQuestionNumber = game.getCurrentQuestionNumber();
                            Question q = game.getQuestions().get(currentQuestionNumber);
                            String className = getClassName(q.getClass().getName());
                            intermediateScreenCtrl.setPointsLabel();
                            goTo("intermediateScreen");
                        }
                    });
                }
                else {
                    int currentQuestionNumber = game.getCurrentQuestionNumber();
                    Question q = game.getQuestions().get(currentQuestionNumber);
                    String className = getClassName(q.getClass().getName());
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (className) {
                                case "MultipleChoiceQuestion":
                                    singlePlayerGameCtrl.setTime(1 + i);
                                    break;

                                case "MostEnergyQuestion":
                                    singlePlayerChooseOptionQuestionCtrl.setTime(i + 1);
                                    break;

                                case "GuessQuestion":
                                    singlePlayerOpenQuestionCtrl.setTime(i + 1);
                                    break;

                                case "InsteadOfQuestion":
                                    singleplayerInsteadOfQuestionCtrl.setTime(i + 1);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    System.out.println(i);
                    MainCtrl.setTimeLeft(i);
                    i--;
                }
            }
        }, 0, 1000);
    }

    /**
     * This method checks if the game is over. It is called after every question.
     */
    public void checkGameStatus() {
        if(game.getCurrentQuestionNumber() + 1 < game.getQuestions().size()){
            game.setCurrentQuestionNumber(game.getCurrentQuestionNumber() + 1);
        }
        else{
            game.setGameOver(true);
        }
        if(game instanceof SinglePlayerGame)
            goToNextSingleplayerQuestion();
        if(game instanceof MultiPlayerGame)
            goToNextMultiplayerQuestion();
    }


    private void goToNextSingleplayerQuestion() {
        if(!game.isGameOver()) {
            singleplayerInGameTimer();
            int currentQuestionNumber;
            int lastQuestionNumber;

            currentQuestionNumber = game.getCurrentQuestionNumber();
            lastQuestionNumber = currentQuestionNumber - 1;

            Question q = game.getQuestions().get(currentQuestionNumber);
            Question qLast = game.getQuestions().get(lastQuestionNumber);

            String className = getClassName(q.getClass().getName());

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                   switchQuestionScreen(className);
                }
            });
        }
        
        else{
            SinglePlayerGame spg = (SinglePlayerGame)this.game;
            Player p =spg.getPlayer();
            p.setJokerCards(null);
            serverUtils.addPlayer(spg.getPlayer());
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    goTo("SinglePlayerLeaderboard");  // PUT LEADERBOARD SCREEN HERE
                }
            });
        }
    }

    /**
     * This method takes an String input that includes the package and the class name and returns only the class name
     * @param className String representing the package and the class name
     * @return String representing  the class name
     */
    public String getClassName(String className){

        int mid=className.lastIndexOf ('.') + 1;
        String finalClsName = className.substring(mid);
        return finalClsName;
    }



    /**
     * This method was creating for testing purposes until we will retrieve a game from the server automatically
     * @param player Instance of Player representing the username of the Player
     * @return A Game instance created for testing purposes
     */
    public Game initialiseSinglePlayerGame(Player player){
       Activity act1 = new Activity("00-shower",
                "00/shower.png",
                "Question 1",
                100,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
       Activity act2 =new Activity("00-shower",
                "00/shower.png",
                "Question 2",
                500,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
       Activity act3 = new Activity("00-smartphone",
                "00/smartphone.png",
                "Question 3",
                10,
                "https://9to5mac.com/2021/09/16/iphone-13-battery-life/");
        Activity act4 = new Activity("00-shower",
                "00/shower.png",
                "Question 4",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        Activity act5 =new Activity("00-shower",
                "00/shower.png",
                "Extra Question",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        Activity act6 = new Activity("00-smartphone",
                "00/smartphone.png",
                "Charging your smartphone at night",
                10,
                "https://9to5mac.com/2021/09/16/iphone-13-battery-life/");

        ArrayList<Activity> options = new ArrayList<>(Arrays.asList(act4, act5, act6));

        Question q2 = new MultipleChoiceQuestion(act2, 2000, "EASY",1);
        Question q3 = new MultipleChoiceQuestion(act3, 2000,"EASY",1);
        Question q4 = new MultipleChoiceQuestion(act4,1000,"EASY",1);
        Question q5 = new MultipleChoiceQuestion(act5,1000,"EASY",1);
        Question q6 = new InsteadOfQuestion(act3, 1000, "EASY", 1, options);
        Question q1 = new MultipleChoiceQuestion(act1,1000,"EASY",1);
        Question q7 = new MostEnergyQuestion(act1,13123,"EASY",5,options);
        Question q8 = new GuessQuestion(act1,2122,"EASY",1212);

        ArrayList<Question> questionArray = new ArrayList<Question>();





        questionArray.add(q7);
        questionArray.add(q5);
        questionArray.add(q6);
        questionArray.add(q8);
        questionArray.add(q7);
        questionArray.add(q5);
        questionArray.add(q6);
        questionArray.add(q8);
        questionArray.add(q7);
        questionArray.add(q5);
        questionArray.add(q6);






        JokerCard j1 = new AdditionalPointsJoker("AdditionalPointsJoker","Description",
                false,
                player,q1);
        JokerCard j2 = new QuestionChangeJoker("QuestionChangeJoker","Description",false);
        JokerCard j3 = new EliminateOptionJoker("EliminateOptionJoker","Description",
                false,(MultipleChoiceQuestion) q1);

        ArrayList<JokerCard> jokerCards = new ArrayList<>(Arrays.asList(j1,j2,j3));

        SinglePlayerGame initialisedGame = new SinglePlayerGame(questionArray,jokerCards,player);

        return initialisedGame;
    }

    public Game getGame() {
        return game;
    }

    public void closeStage() {
        this.primaryStage.close();
    }

    /**
     * This method will take care of all scene switching of the application
     * @param screenName the name of the screen for which it is desired to switch
     */
    public void goTo(String screenName) {
        switch (screenName) {
            case "menu":
                primaryStage.setScene(menu);
                break;
            case "singleGame":
                primaryStage.setScene(singleplayerInsertInfo);
                break;
            case "multiGame":
                primaryStage.setScene(multiPlayerGame);
                break;
            case "credits":
                primaryStage.setScene(credits);
                break;
            case "singleLobby":
                singlePlayerLobbyCtrl.resetJokers();
                jokersStringList = new ArrayList<>();
                primaryStage.setScene(singlePlayerLobby);
                break;
            case "multiLobby":
                multiPlayerLobbyCtrl.setThisPlayer(localPlayer);
                primaryStage.setScene(multiPlayerLobby);
                multiPlayerLobbyCtrl.prepare();
                break;
            case "insertInfoMultiPlayer":
                primaryStage.setScene(multiplayerInsertInfo);
                break;
            case "help":
                primaryStage.setScene(help);
                break;
            case "singleplayerGame":
                primaryStage.setScene(singlePlayerGame);
                break;
            case "SingleplayerChooseOptionQuestionScreen":
                primaryStage.setScene(singlePlayerChooseOptionQuestion);
                break;
            case "SingleplayerOpenQuestion":
                primaryStage.setScene(singlePlayerOpenQuestion);
                break;
            case "SingleplayerInsteadOfQuestion" :
                primaryStage.setScene(singleplayerInsteadOfQuestion);
                break;
            case "MultiplayerInsteadOfQuestion" :
                primaryStage.setScene(multiplayerInsteadOfQuestion);
                break;
            case "MultiPlayerGameCtrl" :
                primaryStage.setScene(multiPlayerGame);
                break;
            case "MultiPlayerOpenQuestionCtrl" :
                primaryStage.setScene(multiPlayerOpenQuestion);
                break;
            case "MultiPlayerChooseOptionQuestion" :
                primaryStage.setScene(multiPlayerChooseOptionQuestion);
                break;
            case "SinglePlayerLeaderboard":
                primaryStage.setScene(singlePlayerLeaderboard);
                singlePlayerLeaderboardCtrl.initialiseLeaderboard();
                break;
            case "intermediateScreen":
                intermediateScreenCtrl.initialiseScene();
                primaryStage.setScene(intermediateScreen);
                break;
            case "admin":
                primaryStage.setScene(admin);
                adminPanelCtrl.instantiateActivities(true, true);
                break;
            case "multiplayerIntermediateScreen":
                //
                //TODO METHOD THAT INITIALIZES THE LEADERBOARD
                //
                primaryStage.setScene(multiPlayerIntermediateScreen);
                break;
            default: primaryStage.setScene(menu);
        }
    }

    public void goToEditActivity(Activity activity) {
        primaryStage.setScene(editActivity);
        editActivityCtrl.initialize(activity);
    }

    /**
     * This method is implemented for the Question Change Joker and we don't need a timer because
     * we have one from the question that has been changed
     */
    public void goToNextQuestionNoTimer(){
        int currentQuestionNumber = game.getCurrentQuestionNumber();
        Question q = game.getQuestions().get(currentQuestionNumber);
        String className = getClassName(q.getClass().getName());
        this.switchQuestionScreen(className);
    }


    public void startSinglePlayerGameCountdown(Player player){
        primaryStage.setScene(singlePlayerStartCountdownScreen);
        singleplayerStartCountdownScreenCtrl.startCountdown(player);
    }

    public void setStringJokers(List<String> checkedStringJokers) {
        this.jokersStringList = checkedStringJokers;
    }
    public List<String> getStringJokers() {
        return this.jokersStringList;
    }

    /**
     * This method creates a player instance with the given username. It also instantiates each joker
     * transforming them from a String to an JokerCard instance
     * @param insertedUsername String representing the username inserted by the user
     * @param stringJokers List of Strings representing the names of the jokers that have to be instantiated.
     * @return An instance of the Player Class
     */
    public Player createPlayer(String insertedUsername, List<String> stringJokers) {
        Player p = new Player(insertedUsername,0);
        List<JokerCard> jokerList = new ArrayList<>();
        System.out.println(stringJokers);
        for (String s : stringJokers) {
            switch (s){
                case "AdditionalPointsJoker":
                    jokerList.add(new AdditionalPointsJoker(p));
                    break;
                case "EliminateOptionJoker":
                    jokerList.add(new EliminateOptionJoker(null));
                    break;
                case "QuestionChangeJoker":
                    jokerList.add(new QuestionChangeJoker());
                    break;
                case "ShortenTimeJoker":
                    jokerList.add(new ShortenTimeJoker(1000,null));
                    break;
                default:
                    break;
            }
        }
        for(int i=0;i<jokerList.size();i++){
            System.out.println(jokerList.get(i));
        }
        System.out.println("---------");
        p.setJokerCards(jokerList);

        return p;
    }

    /**
     * This method initialises the last joker that has been used.
     * @param name String representing the name of the joker that has been used.
     */

    public void setUsedJoker(String name){
        usedJoker = name;
    }

    /**
     *This method handles each type of Joker and calls their useCard methods.
     */
    public void handleJoker() {
        switch (usedJoker){
            case"Additional Points Joker":
                AdditionalPointsJoker pointsJoker =
                        (AdditionalPointsJoker)this.getJoker("Additional Points Joker");
                break;
            case"EliminateOptionJoker":
                EliminateOptionJoker eliminateOptionJokerJoker =
                        (EliminateOptionJoker) this.getJoker("EliminateOptionJoker");
                eliminateOptionJokerJoker.setQuestion((MultipleChoiceQuestion) game.getQuestions().
                        get((game).
                                getCurrentQuestionNumber()));
                eliminateOptionJokerJoker.useCard();
                singlePlayerGameCtrl.initialiseSinglePlayerQuestion();
                ((SinglePlayerGame)game).getPlayer().deleteJoker(eliminateOptionJokerJoker);
                break;
            case"Question Change Joker":
                QuestionChangeJoker questionChangeJoker =
                        (QuestionChangeJoker) this.getJoker("Question Change Joker");
                int questionNr = game.getCurrentQuestionNumber();
                game.setCurrentQuestionNumber(0);
                this.goToNextQuestionNoTimer();
                game.setCurrentQuestionNumber(questionNr);
                ((SinglePlayerGame)game).getPlayer().deleteJoker(questionChangeJoker);
                break;

        }

    }

    /**
     * This method iterates through all the jokers that the player has and returns the one that has the sam name as
     * the parameter
     * @param jokerName String representing the name of the Joker that we need
     * @return An instance of JokerCard
     */

    private JokerCard getJoker(String jokerName) {
        JokerCard returnedJokerCard = null;
        for(JokerCard j : ((SinglePlayerGame)game).getPlayer().getJokerCards()){
            if(j.getName().equals(jokerName))
                returnedJokerCard = j;
        }
        return returnedJokerCard;
    }

    /**
     * This method initialises and goe sto the screen given by the className
     * @param className String representing the className ( the type of question that we have to display)
     */
    public void switchQuestionScreen(String className){
        switch (className) {
            case "MultipleChoiceQuestion":
                singlePlayerGameCtrl.initialiseSinglePlayerQuestion();
                goTo("singleplayerGame");
                break;

            case "MostEnergyQuestion":
                singlePlayerChooseOptionQuestionCtrl.initialiseMostEnergyQuestion();
                goTo("SingleplayerChooseOptionQuestionScreen");
                break;

            case "GuessQuestion":
                singlePlayerOpenQuestionCtrl.initialiseSinglePlayerOpenQuestion();
                goTo("SingleplayerOpenQuestion");
                break;

            case "InsteadOfQuestion":
                singleplayerInsteadOfQuestionCtrl.initialiseSinglePlayerInsteadOfQuestion();
                goTo("SingleplayerInsteadOfQuestion");
                break;

            default:
                break;
        }
    }



    public void setExitedGame(boolean exitedGame) {
        this.exitedGame = exitedGame;

    }
    public ServerUtils getServer(){
        return  serverUtils;
    }


    /**
     * This method will call serverUtils to update the activity provided in the repository
     * @param activity to update
     * @return the updated activity
     */
    public Activity editActivity(Activity activity) {
        return serverUtils.editActivity(activity);
    }


    public Player getLocalPlayer() {
        return localPlayer;
    }

    public void setLocalPlayer(Player player) {
        this.localPlayer = player;
    }

    /**
     * This method starts the websocket connection for Emoji instances when the game is started, the method will be
     * called from the Lobby Screen , when the start button is pressed. It also initialises the emojis that are
     * retrieved from other clients.
     */
    public void startScanningEmojis(){
        serverUtils.registerForEmoji("/topic/emojis",e->{
//            String currentQuestionScreen = getClassName(game.getQuestions().
//                    get(game.getCurrentQuestionNumber())
//                    .getClass().toString());
            String currentQuestionScreen = getClassName(this.getQuestion()
                  .getClass().toString());
            switch (currentQuestionScreen) {
                case "MultipleChoiceQuestion":
                    Platform.runLater(()->{multiPlayerGameCtrl.initialiseEmoji(e);});
                    break;

                case "MostEnergyQuestion":
                    Platform.runLater(()->{multiPlayerChooseOptionQuestionCtrl.initialiseEmoji(e);});
                    break;

                case "GuessQuestion":
                    Platform.runLater(()->{multiPlayerOpenQuestionCtrl.initialiseEmoji(e);});
                    break;

                case "InsteadOfQuestion":
                    Platform.runLater(()->{multiplayerInsteadOfQuestionCtrl.initialiseEmoji(e);});
                    break;

                default:
                    break;
        }
    });
    }

    /**
     * This method creates a mock question
     * @return An instance of anstract class Question
     */
    private Question getQuestion() {
        Activity act1 = new Activity("00-shower",
                "00/shower.png",
                "Question 1",
                100,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");

        Activity act3 = new Activity("00-smartphone",
                "00/smartphone.png",
                "Question 3",
                10,
                "https://9to5mac.com/2021/09/16/iphone-13-battery-life/");
        Activity act4 = new Activity("00-shower",
                "00/shower.png",
                "Question 4",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        Activity act5 =new Activity("00-shower",
                "00/shower.png",
                "Extra Question",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        Activity act6 = new Activity("00-smartphone",
                "00/smartphone.png",
                "Charging your smartphone at night",
                10,
                "https://9to5mac.com/2021/09/16/iphone-13-battery-life/");
        ArrayList<Activity> options = new ArrayList<>(Arrays.asList(act4, act5, act6));
        Question q4 = new MultipleChoiceQuestion(act4,1000,"EASY",1);
        //Question q6 = new InsteadOfQuestion(act3, 1000, "EASY", 1, options);

       // Question q7 = new MostEnergyQuestion(act1,13123,"EASY",5,options);
        //Question q8 = new GuessQuestion(act1,2122,"EASY",1212);
        return q4;

    }

    /**
     * The method includes the logic of the multiplayer game but it is not fully implemented.
     */
    public void startMultiPlayerGame(){
        startScanningEmojis();
        localPlayer = new Player("usernameee",1000);
    }






    public void playMultiPLayerGame(){
//        game = initialiseMultiPlayerGame(); //TODO REPLACE WITH PROPER MULTIPLAYER GAME INITIALIZER
        goToNextMultiplayerQuestion();
    }

    public void goToNextMultiplayerQuestion(){
        if(!game.isGameOver()) {
            multiplayerInGameTimer();
            int currentQuestionNumber;
            int lastQuestionNumber;

            currentQuestionNumber = game.getCurrentQuestionNumber();
            lastQuestionNumber = currentQuestionNumber - 1;

            Question q = game.getQuestions().get(currentQuestionNumber);
            Question qLast = game.getQuestions().get(lastQuestionNumber);

            String className = getClassName(q.getClass().getName());

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    switchQuestionScreen(className);
                } //TODO NEED METHOD THAT INITIALZES MULTIPLAYER QUESTIONS
            });
        }
        else{
            goTo("menu");
            //TODO METHOD THAT UPDATES THE SCORE
            //TODO METHOD THAT INITIALIZES THE LEADERBOARD SCREEN
        }
    }

    public void multiplayerInGameTimer(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = game.getQuestions().get(game.getCurrentQuestionNumber()).getAllowedTime();
            
            @Override
            public void run() {
                //
                //TODO CHECK IF A PLAYER LEFT GAME AND REMOVE THEM FROM THE PLAYER ARRAYLIST IN THE GAME OBJECT
                //
                if(game instanceof MultiPlayerGame){
                    MultiPlayerGame multiPlayerGame = (MultiPlayerGame) game;
                    if(multiPlayerGame.getPlayers().size() == 0){
                        //
                        //TODO METHOD THAT DELETES THE GAME FROM ARRAYLIST OF GAMES
                        //
                    }
                }
                if(i <= 0) {
                    timer.cancel();
                    //
                    //TODO METHOD THAT UPDATES THE CURRENT SCORES OF ALL PLAYERS
                    //
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            goTo("multiplayerIntermediateScreen");
                        }
                    });
                }
                else{
                    int currentQuestionNumber = game.getCurrentQuestionNumber();
                    Question q = game.getQuestions().get(currentQuestionNumber);
                    String className = getClassName(q.getClass().getName());
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (className) {
                                case "MultipleChoiceQuestion":
                                    multiPlayerGameCtrl.setTime(1 + i);
                                    break;

                                case "MostEnergyQuestion":
                                    multiPlayerChooseOptionQuestionCtrl.setTime(i + 1);
                                    break;

                                    case "GuessQuestion":
                                    multiPlayerOpenQuestionCtrl.setTime(i + 1);
                                    break;

                                case "InsteadOfQuestion":
                                    multiplayerInsteadOfQuestionCtrl.setTime(i + 1);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                }
                i--;
            }
        },0, 1000);
    }

    public Game initialiseMultiPlayerGame(){
        Activity act1 = new Activity("00-shower",
                "00/shower.png",
                "Question 1",
                100,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        Activity act2 =new Activity("00-shower",
                "00/shower.png",
                "Question 2",
                500,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        Activity act3 = new Activity("00-smartphone",
                "00/smartphone.png",
                "Question 3",
                10,
                "https://9to5mac.com/2021/09/16/iphone-13-battery-life/");
        Activity act4 = new Activity("00-shower",
                "00/shower.png",
                "Question 4",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        Activity act5 =new Activity("00-shower",
                "00/shower.png",
                "Question 5",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        Activity act6 = new Activity("00-smartphone",
                "00/smartphone.png",
                "Question 6",
                10,
                "https://9to5mac.com/2021/09/16/iphone-13-battery-life/");
        Activity extraact = new Activity("00-smartphone",
                "00/smartphone.png",
                "Extra Question",
                10,
                "https://9to5mac.com/2021/09/16/iphone-13-battery-life/");

        ArrayList<Activity> options = new ArrayList<>(Arrays.asList(act4, act5, act6));

        Question q1 = new MultipleChoiceQuestion(act1, 2000, "EASY",5);
        Question q2 = new MultipleChoiceQuestion(act2, 2000,"EASY",5);
        Question q3 = new MultipleChoiceQuestion(act3,1000,"EASY",5);
        Question q4 = new MultipleChoiceQuestion(act4,1000,"EASY",5);
        Question q5 = new InsteadOfQuestion(act5, 1000, "EASY", 5, options);
        Question q6 = new MultipleChoiceQuestion(act6,1000,"EASY",5);
        Question extraquestion = new MostEnergyQuestion(extraact,13123,"EASY",5,options);
        
        ArrayList<Question> questionArray = new ArrayList<Question>();

        questionArray.add(extraquestion);
        questionArray.add(q1);
        questionArray.add(q2);
        questionArray.add(q3);
        questionArray.add(q4);
        questionArray.add(q5);
        questionArray.add(q6);

        Player player1 = new Player("Som",0);
        Player player2 = new Player("Som",0);
        Player player3 = new Player("Alex", 0);
        Player player4 = new Player("Som",0);
        Player player5 = new Player("Ansh", 0);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        
        JokerCard j1 = new AdditionalPointsJoker("AdditionalPointsJoker","Description",
                false,
                player1,q1);
        JokerCard j2 = new QuestionChangeJoker("QuestionChangeJoker","Description",false);
        JokerCard j3 = new EliminateOptionJoker("EliminateOptionJoker","Description",
                false,(MultipleChoiceQuestion) q1);

        ArrayList<JokerCard> jokerCards = new ArrayList<>(Arrays.asList(j1,j2,j3));

        MultiPlayerGame initialisedGame = new MultiPlayerGame(questionArray,jokerCards,players);

        return initialisedGame;
    }
}


