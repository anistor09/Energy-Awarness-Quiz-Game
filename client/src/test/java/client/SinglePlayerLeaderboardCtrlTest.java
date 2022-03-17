package client;

import client.scenes.MainCtrl;
import client.scenes.SinglePlayerLeaderboardCtrl;
import client.utils.ServerUtils;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglePlayerLeaderboardCtrlTest {

    @Test
    void initialiseLeaderboard() {
        SinglePlayerLeaderboardCtrl s = new SinglePlayerLeaderboardCtrl(new MainCtrl(), new ServerUtils());
        s.goToMenu(new ActionEvent());
        s.initialiseLeaderboard();
    }
}