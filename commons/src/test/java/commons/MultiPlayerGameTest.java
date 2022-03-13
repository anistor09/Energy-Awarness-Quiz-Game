package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;



class MultiPlayerGameTest {

    Player player1 = new Player("som", 320);
    Player player2 = new Player("ansh", 220);
    Player player3 = new Player("alex", 420);
    Player player4 = new Player("max", 340);
    Player player5 = new Player("rafael", 330);

    private GuessQuestion q1;
    private GuessQuestion q2;

    private InsteadOfQuestion q3;
    private InsteadOfQuestion q4;

    private MultipleChoiceQuestion q5;
    private MultipleChoiceQuestion q6;

    private MostEnergyQuestion q7;
    private MostEnergyQuestion q8;

    MultiPlayerGame mpg;
    MultiPlayerGame mpg2;
    MultiPlayerGame mpg3;

    @BeforeEach
    void setUp() {

        q1 = new GuessQuestion(new Activity(1L, "Cook one egg", 1000), 1000,
                "EASY", 30);

        q2 = new GuessQuestion(new Activity(2L, "Cook two eggs", 2000), 1000,
                60);

        q3 = new InsteadOfQuestion(new Activity(4L, "Run the washing machine", 3000),
                2000, 40, new ArrayList<Activity>
                (Arrays.asList(new Activity(2L, "Cook five eggs", 5000),
                        new Activity(3L, "Charge phone", 2000))));

        q4 = new InsteadOfQuestion(new Activity(2L, "Cook two eggs", 2000), 2000,
                40, new ArrayList<Activity>
                (Arrays.asList(new Activity(3L, "Charge phone", 2000),
                        new Activity(4L, "Run the washing machine", 3000))));


        q5 = new MultipleChoiceQuestion(new Activity(2L, "Cook two eggs", 2000), 2000,
                30);

        q6 = new MultipleChoiceQuestion(new Activity(1L, "Cook one egg", 1000),
                3000, "HARD", 30);

        q7 = new MostEnergyQuestion(new Activity(2L, "Cook five eggs", 5000),
                2000, 40, new ArrayList<Activity>(
                Arrays.asList(new Activity(1L, "Cook one egg", 1000),
                        new Activity(2L, "Cook two eggs", 2000))));

        q8 = new MostEnergyQuestion(new Activity(1L, "Cook one egg", 1000),
                2000, 40, new ArrayList<Activity>(
                Arrays.asList(new Activity(1L, "Cook one egg", 1000),
                        new Activity(2L, "Cook two eggs", 2000))
        ));
        
        AdditionalPointsJoker additionalPoints1 = new AdditionalPointsJoker("Additional",
                "Adds 10 additional points if you answer correctly",
                false,
                10, player1);

        ShortenTimeJoker extend = new ShortenTimeJoker("10 seconds extend card",
                "Gives you 10sec more time to answer this question",
                false, 10, q6);

        mpg = new MultiPlayerGame(
                new ArrayList<Question>(Arrays.asList(q1, q2, q3, q4, q5, q6, q7, q8)),
                new ArrayList<JokerCard>(Arrays.asList(additionalPoints1, extend)),
                new ArrayList<Player>(Arrays.asList(player1, player2, player3, player4, player5)));

        mpg2 = new MultiPlayerGame(
                new ArrayList<Question>(Arrays.asList(q1, q2, q3, q4, q5, q6, q7, q8)),
                new ArrayList<JokerCard>(Arrays.asList(additionalPoints1, extend)),
                new ArrayList<Player>(Arrays.asList(player1, player2, player3, player4, player5)));

        mpg3 = new MultiPlayerGame(
                new ArrayList<Question>(Arrays.asList(q1, q2, q3, q4, q5, q6, q7)),
                new ArrayList<JokerCard>(Arrays.asList(additionalPoints1, extend)),
                new ArrayList<Player>(Arrays.asList(player1, player2, player3, player4, player5)));
    }


    @Test
    void checkConstructor() {
        assertNotNull(mpg);
    }

    @Test
    void testEquals() {
        assertTrue(mpg.equals(mpg2));
    }

    @Test
    void testEqualHashCode() {
        assertTrue(mpg.hashCode() == mpg2.hashCode());
    }

    @Test
    void testNotEquals() {
        assertNotEquals(mpg, mpg3);
    }
}
