package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SinglePlayerLeaderboardCtrl {
    @FXML
    private Label name1;
    @FXML
    private Label score1;

    @FXML
    private Label name2;
    @FXML
    private Label score2;

    @FXML
    private Label name3;
    @FXML
    private Label score3;

    @FXML
    private Label name4;
    @FXML
    private Label score4;

    @FXML
    private Label name5;
    @FXML
    private Label score5;

    @FXML
    private Label name6;
    @FXML
    private Label score6;

    @FXML
    private Label name7;
    @FXML
    private Label score7;

    @FXML
    private Label name8;
    @FXML
    private Label score8;

    @FXML
    private Label name9;
    @FXML
    private Label score9;

    @FXML
    private Label name10;
    @FXML
    private Label score10;

    private final MainCtrl mainCtrl;
    private final ServerUtils serverUtils;

    @Inject
    public SinglePlayerLeaderboardCtrl(MainCtrl mainCtrl, ServerUtils serverUtils) {
        this.mainCtrl = mainCtrl;
        this.serverUtils = serverUtils;
    }


    public void goToMenu(ActionEvent actionEvent) {
        name1.setText("hello, this is a test");
        mainCtrl.goTo("menu");
    }

    public void goToSPLobby(ActionEvent actionEvent) {
        mainCtrl.goTo("singleLobby");
    }

    /**
     * Retrieves all players as a List from the repository and sorts the list in descending order of currentScore.
     * Usernames and scores are mapped to the leaderboard's corresponding labels.
     */
    public void initialiseLeaderboard() {
        // get list of players from server util
        List<Player> players = serverUtils.getLeaderboard();

        int numOfPlayers = players.size();

        System.out.println(numOfPlayers);

        // if there are fewer than 10 players, we only want to set the first N rows in the leaderboard
        int min = Math.min(10, numOfPlayers);


        // sort leaderboard in descending order from 1 to 10

        players.sort((o1, o2) -> o2.getCurrentScore() - o1.getCurrentScore());

        // truncate list to only get the first 10

        players = players.subList(0, min);

        name1.setText(players.get(0).getUsername());

        Label[] names = {name1, name2, name3, name4, name5, name6, name7, name8, name9, name10};
        Label[] scores = {score1, score2, score3, score4, score5, score6, score7, score8, score9, score10};

        // set name and score labels to their corresponding values retrieved from the server.
        for (int i = 0; i < min; i++) {
            names[i].setText(players.get(i).getUsername());
            scores[i].setText(String.valueOf(players.get(i).getCurrentScore()));
        }

        for (Label name : names) {
            System.out.println(name.getAccessibleText());
        }

    }
}
