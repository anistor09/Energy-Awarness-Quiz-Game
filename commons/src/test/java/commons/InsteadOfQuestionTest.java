package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsteadOfQuestionTest {

    private InsteadOfQuestion q1;
    private InsteadOfQuestion q2;
    private InsteadOfQuestion q3;
    private InsteadOfQuestion q4;
    private InsteadOfQuestion q5;

    private Activity act1;
    private Activity act2;
    private Activity act3;
    private Activity act4;
    private Activity act5;
    private Activity act6;

    @BeforeEach
    void setUp() {
        act1 = new Activity(1L, "Cook one egg", 1000);
        act2 = new Activity(1L, "Cook one egg", 1000);
        act3 = new Activity(2L, "Cook two eggs", 2000);
        act4 = new Activity(2L, "Cook five eggs", 5000);
        act5 = new Activity(3L, "Charge phone", 2000);
        act6 = new Activity(4L, "Run the washing machine", 3000);

        q1 = new InsteadOfQuestion(act1, 2000, 40, new ArrayList<Activity>(
                Arrays.asList(act2, act3)
        ));
        q2 = new InsteadOfQuestion(act2, 2000, 40, new ArrayList<Activity>(
                Arrays.asList(act1, act3)
        ));
        q3 = new InsteadOfQuestion(act6, 2000, 40, new ArrayList<Activity>(
                Arrays.asList(act4, act5)
        ));
        q4 = new InsteadOfQuestion(act3, 2000, 40, new ArrayList<Activity>(
                Arrays.asList(act5, act6)
        ));
    }

    @Test
    void checkConstructor() {
        assertNotNull(q1);
    }

    @Test
    void testEquals() {
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEqualHashCode() {
        assertTrue(q1.hashCode() == q2.hashCode());
    }

    @Test
    void testNotEquals() {
        assertNotEquals(q2, q4);
    }


    @Test
    void compareActivities() {
        assertEquals(3, q3.compareActivities(act1));
    }

    @Test
    void stringSubstituteActivities() {
        String expected = "Instead of \nRun the washing machine\nYou could \nCook one egg";
        assertEquals(expected, q3.substituteActivity(act1));
    }
}