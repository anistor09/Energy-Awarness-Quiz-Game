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


    /**
     * This initializes the different screens so that we can transition between them
     * @param primaryStage = This is the Stage where the game will be displayed
     * @param menuPair = This is the pair which contains the information for the menu screen
     */
    public void initialize(Stage primaryStage, Pair<MenuCtrl, Parent> menuPair) {
        this.primaryStage = primaryStage;
        this.menuCtrl = menuPair.getKey();
        this.menu = new Scene(menuPair.getValue());

        showMenu();
        primaryStage.show();
    }


    public void showMenu() {
        primaryStage.setTitle("Quizzz");
        primaryStage.setScene(menu);
    }

    public void closeStage() {
        this.primaryStage.close();
    }
}