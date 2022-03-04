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
     * This method starts the game and initializes every different screen
     * @param primaryStage is the Stage where the game will take place in
     * @throws IOException is thrown incase of an error with the IO
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Pair<MenuCtrl, Parent> menu = FXML.load(MenuCtrl.class, "client", "scenes", "Menu.fxml");
        Pair<SinglePlayerLobbyController, Parent> singlePlayerLobbyControllerParentPair =
                FXML.load(SinglePlayerLobbyController.class, "client", "scenes", "SingleplayerLobbyScreen.fxml");
        Pair<MultiPlayerLobbyController, Parent> multiPlayerLobbyControllerParentPair =
                FXML.load(MultiPlayerLobbyController.class, "client", "scenes", "MultiplayerLobbyScreen.fxml");
        Pair<CreditsController, Parent> creditsControllerParentPair= FXML.load(CreditsController.class,
                "client", "scenes", "CreditsScreen.fxml");
        MainCtrl mainCtrl = INJECTOR.getInstance(MainCtrl.class);
        mainCtrl.initialize(primaryStage, menu, singlePlayerLobbyControllerParentPair,
                multiPlayerLobbyControllerParentPair, creditsControllerParentPair);
    }
}