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
    private Stage singlePlayerLobbyStage;


    private MenuCtrl menuCtrl;
    private Scene menu;

    private SinglePlayerLobbyController singlePlayerLobbyController;
    private Scene singlePlayerLobby;

    private MultiPlayerLobbyController multiPlayerLobbyController;
    private Scene multiPlayerLobby;

    private CreditsController creditsController;
    private Scene credits;

    /**
     * This method inistializes all the screens that will be used throughout the game
     * @param primaryStage is the Stage where the game will take place
     * @param menuPair is the Pair which contains the information for the menu screen
     * @param singlePlayerLobbyControllerParentPair is the Pair which contains the information for the Single-player
     *                                             lobby screen
     * @param multiPlayerLobbyControllerParentPair is the Pair which contains the information for the multiplayer lobby
     *                                            screen
     * @param creditsControllerParentPair is the Pair which contains the information for the credits screen
     */
    public void initialize(Stage primaryStage, Pair<MenuCtrl, Parent> menuPair, Pair<SinglePlayerLobbyController, Parent> singlePlayerLobbyControllerParentPair, Pair<MultiPlayerLobbyController, Parent> multiPlayerLobbyControllerParentPair, Pair<CreditsController, Parent> creditsControllerParentPair) {
        this.primaryStage = primaryStage;
        this.menuCtrl = menuPair.getKey();
        this.menu = new Scene(menuPair.getValue());
        this.singlePlayerLobbyController = singlePlayerLobbyControllerParentPair.getKey();
        this.singlePlayerLobby = new Scene(singlePlayerLobbyControllerParentPair.getValue());
        this.multiPlayerLobbyController = multiPlayerLobbyControllerParentPair.getKey();
        this.multiPlayerLobby = new Scene(multiPlayerLobbyControllerParentPair.getValue());
        this.creditsController = creditsControllerParentPair.getKey();
        this.credits = new Scene(creditsControllerParentPair.getValue());

        showMenu();
        primaryStage.show();
    }

    public void goToSinglePlayerLobby(){
        primaryStage.setScene(singlePlayerLobby);
    }

    public void goToMultiPlayerLobby(){
        primaryStage.setScene(multiPlayerLobby);
    }

    public void goToCredits() {
        primaryStage.setScene(credits);
    }



    public void showMenu() {
        primaryStage.setTitle("Quizzz");
        primaryStage.setScene(menu);
    }

    public void closeStage() {
        this.primaryStage.close();
    }

    public void Return(String screenName) {
        if(screenName.equals("menu")){
            primaryStage.setScene(menu);
        }
    }
}