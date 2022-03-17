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

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;


public class MainCtrl {

    private Stage primaryStage;

    private MenuCtrl menuCtrl;
    private Scene menu;

    private SinglePlayerLobbyCtrl singlePlayerLobbyCtrl;
    private Scene singlePlayerLobby;

    private MultiPlayerLobbyCtrl multiPlayerLobbyCtrl;
    private Scene multiPlayerLobby;

    private SinglePlayerGameCtrl singlePlayerGameCtrl;
    private Scene singlePlayerGame;

    private MultiPlayerGameCtrl multiPlayerGameCtrl;
    private Scene multiPlayerGame;

    private CreditsCtrl creditsCtrl;
    private Scene credits;

    private MultiPlayerChooseOptionQuestionCtrl multiPlayerChooseOptionQuestionCtrl;
    private Scene multiPlayerChooseOptionQuestion;

    private SinglePlayerChooseOptionQuestionCtrl singlePlayerChooseOptionQuestionCtrl;
    private Scene singlePlayerChooseOptionQuestion;

    private MultiPlayerOpenQuestionCtrl multiPlayerOpenQuestionCtrl;
    private Scene multiPlayerOpenQuestion;

    private SinglePlayerOpenQuestionCtrl singlePlayerOpenQuestionCtrl;
    private Scene singlePlayerOpenQuestion;

    private InsertUsernameMultiplayerCtrl multiplayerInsertInfoCtrl;
    private Scene  multiplayerInsertInfo;

    private InsertUsernameSingleplayerCtrl singleplayerInsertInfoCtrl;
    private Scene singleplayerInsertInfo;

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
     * @param insertInfoMultiplayer
     * @param insertInfoSingleplayer the pair containing the singlePlayer username controller and its fxml file
     *                               "Parent"
     */
    public void initialize(Stage primaryStage, Pair<MenuCtrl, Parent> menuPair, Pair<SinglePlayerLobbyCtrl,
            Parent> singlePlayerLobbyControllerParentPair, Pair<MultiPlayerLobbyCtrl,
            Parent> multiPlayerLobbyControllerParentPair, Pair<CreditsCtrl, Parent> creditsControllerParentPair,
                           Pair<SinglePlayerGameCtrl, Parent> singlePlayerGamePair, Pair<MultiPlayerGameCtrl,
            Parent> multiPlayerGamePair,
                           Pair<MultiPlayerChooseOptionQuestionCtrl, Parent>
                                   multiPlayerChooseOptionQuestionControllerParentPair,
                           Pair<SinglePlayerChooseOptionQuestionCtrl, Parent>
                                   singlePlayerChooseOptionQuestionControllerParentPair,
                           Pair<MultiPlayerOpenQuestionCtrl, Parent> multiPlayerOpenQuestionControllerParentPair,
                           Pair<SinglePlayerOpenQuestionCtrl, Parent>
                                   singlePlayerOpenQuestionControllerParentPair,
                                   Pair<InsertUsernameMultiplayerCtrl, Parent> insertInfoMultiplayer,
                           Pair<InsertUsernameSingleplayerCtrl, Parent> insertInfoSingleplayer) {

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
        this.singleplayerInsertInfoCtrl = insertInfoSingleplayer.getKey();
        this.singleplayerInsertInfo = new Scene(insertInfoSingleplayer.getValue());



        primaryStage.setTitle("Quizzz");
        goTo("menu");
        primaryStage.show();

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
                primaryStage.setScene(singlePlayerGame);
                break;
            case "multiGame":
                primaryStage.setScene(multiPlayerGame);
                break;
            case "credits":
                primaryStage.setScene(credits);
                break;
            case "singleLobby":
                primaryStage.setScene(singlePlayerLobby);
                break;
            case "multiLobby":
                primaryStage.setScene(multiPlayerLobby);
                break;
            case "insertInfoMultiPlayer":
                primaryStage.setScene(multiplayerInsertInfo);
                break;
            case "insertInfoSinglePlayer":
                primaryStage.setScene(singleplayerInsertInfo);
                break;
            default: primaryStage.setScene(menu);

        }
    }
}
