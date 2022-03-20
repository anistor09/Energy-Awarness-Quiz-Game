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
package client;

import static com.google.inject.Guice.createInjector;

import java.io.IOException;
import java.net.URISyntaxException;


import client.scenes.*;

import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Pair;


public class Main extends Application {

    private static final Injector INJECTOR = createInjector(new MyModule());
    private static final MyFXML FXML = new MyFXML(INJECTOR);

    public static void main(String[] args) throws URISyntaxException, IOException {
        launch();
    }

    /**
     * This method will prepare all the Pair of controller and fxml files for every scene. It will then call the
     * initialize method of the mainCtrl class
     * @param primaryStage is the Stage where the game will take place in
     * @throws IOException is thrown in case of an error with the IO
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Pair<MenuCtrl, Parent> menu = FXML.load(MenuCtrl.class, "client", "scenes", "Menu.fxml");

        Pair<SinglePlayerLobbyCtrl, Parent> singleLobby =
                FXML.load(SinglePlayerLobbyCtrl.class, "client", "scenes", "SingleplayerLobbyScreen.fxml");

        Pair<MultiPlayerLobbyCtrl, Parent> multiLobby =
                FXML.load(MultiPlayerLobbyCtrl.class, "client", "scenes", "MultiplayerLobbyScreen.fxml");

        Pair<SinglePlayerGameCtrl, Parent> singleGame =
                FXML.load(SinglePlayerGameCtrl.class, "client", "scenes", "SingleplayerGame.fxml");

        Pair<MultiPlayerGameCtrl, Parent> multiGame =
                FXML.load(MultiPlayerGameCtrl.class, "client", "scenes", "MultiplayerGame.fxml");

        Pair<CreditsCtrl, Parent> credits =
                FXML.load(CreditsCtrl.class, "client", "scenes", "CreditsScreen.fxml");

        Pair<MultiPlayerChooseOptionQuestionCtrl, Parent> multiPlayerChooseOptionQuestionControllerParentPair =
                FXML.load(MultiPlayerChooseOptionQuestionCtrl.class, "client", "scenes",
                        "MultiplayerChooseOptionQuestionScreen.fxml");

        Pair<SinglePlayerChooseOptionQuestionCtrl, Parent> singlePlayerChooseOptionQuestionControllerParentPair =
                FXML.load(SinglePlayerChooseOptionQuestionCtrl.class, "client", "scenes",
                        "SingleplayerChooseOptionQuestionScreen.fxml");

        Pair<MultiPlayerOpenQuestionCtrl, Parent> multiPlayerOpenQuestionControllerParentPair =
                FXML.load(MultiPlayerOpenQuestionCtrl.class, "client", "scenes",
                        "MultiplayerOpenQuestion.fxml");

        Pair<SinglePlayerOpenQuestionCtrl, Parent> singlePlayerOpenQuestionControllerParentPair =
                FXML.load(SinglePlayerOpenQuestionCtrl.class, "client", "scenes",
                        "SingleplayerOpenQuestion.fxml");
        Pair<InsertUsernameMultiplayerCtrl, Parent> insertInfoMultiplayer =
                FXML.load(InsertUsernameMultiplayerCtrl.class, 
                "client", "scenes", "InsertUsernameMultiplayer.fxml");
        Pair<HelpCtrl, Parent> helpCtrlParentPair =
                FXML.load(HelpCtrl.class, "client", "scenes", "HelpScreen.fxml");
        Pair<InsertUsernameSinglePlayerCtrl, Parent> insertInfoSingleplayer =
                FXML.load(InsertUsernameSinglePlayerCtrl.class,
                        "client", "scenes", "InsertUsernameSingleplayer.fxml");
        Pair<SingleplayerInsteadOfQuestionCtrl, Parent> singleplayerInsteadOfQuestionCtrlParentPair =
                FXML.load(SingleplayerInsteadOfQuestionCtrl.class,
                        "client", "scenes", "SingleplayerInsteadOfQuestion.fxml");
        Pair<MultiplayerInsteadOfQuestionCtrl, Parent> multiplayerInsteadOfQuestionCtrlParentPair =
                FXML.load(MultiplayerInsteadOfQuestionCtrl.class,
                        "client", "scenes", "MultiplayerInsteadOfQuestion.fxml");

        Pair<SinglePlayerLeaderboardCtrl, Parent> singlePlayerLeaderboardCtrlParentPair =
                FXML.load(SinglePlayerLeaderboardCtrl.class, "client", "scenes",
                        "SinglePlayerLeaderboard.fxml");

        Pair<IntermediateScreenCtrl, Parent> intermediateScreenCtrlParentPair =
                FXML.load(IntermediateScreenCtrl.class, "client", "scenes", "IntermediateScreen.fxml");

        MainCtrl mainCtrl = INJECTOR.getInstance(MainCtrl.class);
        mainCtrl.initialize(primaryStage, menu, singleLobby,
                multiLobby, credits, singleGame, multiGame,
                multiPlayerChooseOptionQuestionControllerParentPair,
                singlePlayerChooseOptionQuestionControllerParentPair,
                multiPlayerOpenQuestionControllerParentPair,
                singlePlayerOpenQuestionControllerParentPair,
                insertInfoMultiplayer,helpCtrlParentPair, insertInfoSingleplayer,
                singlePlayerLeaderboardCtrlParentPair,
                singleplayerInsteadOfQuestionCtrlParentPair,
                multiplayerInsteadOfQuestionCtrlParentPair, 
                intermediateScreenCtrlParentPair);


    }
}
