package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MostEnergyQuestionTest {
    private MostEnergyQuestion q1;
    private MostEnergyQuestion q2;
    private MostEnergyQuestion q3;
    private MostEnergyQuestion q4;
    private MostEnergyQuestion q5;

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

        q1 = new MostEnergyQuestion(act1, 2000, 40, new ArrayList<Activity>(
                Arrays.asList(act2, act3)
        ));
        q2 = new MostEnergyQuestion(act2, 2000, 40, new ArrayList<Activity>(
                Arrays.asList(act1, act3)
        ));
        q3 = new MostEnergyQuestion(act6, 2000, 40, new ArrayList<Activity>(
                Arrays.asList(act4, act5)
        ));
        q4 = new MostEnergyQuestion(act3, 2000, 40, new ArrayList<Activity>(
                Arrays.asList(act5, act6)
        ));

        q5 = new MostEnergyQuestion(act4, 2000, 40, new ArrayList<Activity>(
                Arrays.asList(act2, act3)
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
    void checkValidity() {
        assertFalse(q3.checkValidity());
        assertTrue(q5.checkValidity());
    }
}