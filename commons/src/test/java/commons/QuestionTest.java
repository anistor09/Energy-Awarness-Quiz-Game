package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class QuestionTest {

    private Question q1;
    private Question q2;
    private Question q3;
    private Question q4;
    private Question q5;

    private Activity act1;
    private Activity act2;
    private Activity act3;



    @BeforeEach
    public void setup() {
        act1 = new Activity(1L, "Cook one egg", 1000);
        act2 = new Activity(1L, "Cook one egg", 1000);
        act3 = new Activity(2L, "Cook two eggs", 2000);

        q1 = new Question(act1, 1000, "EASY");
        q2 = new Question(act2, 2000, "MEDIUM");
        q3 = new Question(act3, 3000, "HARD");
        q4 = new Question(act3, 3000, "HARD");
        q5 = new Question(act3, 2000);

    }

    @Test
    void checkConstructor() {
        assertNotNull(q1);
    }

    @Test
    void checkConstructorWithoutDifficulty() {
        assertNotNull(q5);
    }

    @Test
    void checkIllegalArgumentConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new Question(act1, 2000, "TEST FAILURE"));
    }

    @Test
    void testEquals() {
        assertTrue(q3.equals(q4));
    }

    @Test
    void testEqualHashCode() {
        assertTrue(q3.hashCode() == q4.hashCode());
    }

    @Test
    void testNotEquals() {
        assertNotEquals(q2, q5);
    }

    @Test
    void getDifficulty() {
        assertEquals(q5.getDifficulty(),"EASY");
    }

    @Test
    void checkAnswerRangeEasy() {
        ArrayList<Double> options = q1.getOptions();

        // for each option check whether they are greater than 799 and less than 1201
        for (Double option : options) {
            assertTrue((option > 799) && (option < 1201));
        }
    }

    @Test
    void checkAnswerRangeMedium() {
        ArrayList<Double> options = q2.getOptions();

        // for each option check whether they are greater than 899 and less than 1101
        for (Double option : options) {
            assertTrue((option > 899) && (option < 1101));
        }
    }

    @Test
    void checkAnswerRangeHard() {
        ArrayList<Double> options = q3.getOptions();

        // for each option check whether they are greater than 1899 and less than 2101
        for (Double option : options) {
            assertTrue((option > 1899) && (option < 2101));
        }
    }

    @Test
    void getAvailablePointsTest() {
        assertEquals(2000, q2.getAvailablePoints());
    }



}