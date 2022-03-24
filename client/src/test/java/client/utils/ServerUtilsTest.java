package client.utils;

import commons.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServerUtilsTest {

    @Disabled
    @Test
    void addPlayer() {
        ServerUtils su= new ServerUtils();
        Activity act1 = new Activity("00-shower",
                "00/shower.png",
                "Question 1",
                100,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        Player p = new Player("asdasd",213);
        Question q1 = new MultipleChoiceQuestion(act1,1000,"EASY",1);
        JokerCard j1 = new AdditionalPointsJoker("AdditionalPointsJoker","Description",
                false,
                p,q1);
        JokerCard j2 = new QuestionChangeJoker("QuestionChangeJoker","Description",false);
        JokerCard j3 = new EliminateOptionJoker("EliminateOptionJoker","Description",
                false,(MultipleChoiceQuestion) q1);

        ArrayList<JokerCard> jokerCards = new ArrayList<>(Arrays.asList(j1,j2,j3));
        p.setJokerCards(jokerCards);
        p.setJokerCards(null);
        su.addPlayer(p);
    }

    @Test
    void testSocket() {
            ServerUtils sut = new ServerUtils();
        MultiPlayerGame game = sut.getCurrentMultiplayerGame(new Player("test", 39));
        assertNotNull(game);
        sut.registerForNewPlayers("/topic/updateLobby", System.out::println);
    }
}