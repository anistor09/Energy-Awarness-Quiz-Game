package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ActivityTest {
    private Activity act1;
    private Activity act2;
    private Activity act3;

    @BeforeEach
    public void setup() {
        act1 = new Activity(1L, "Cook one egg", 10);
        act2 = new Activity(1L, "Cook one egg", 10);
        act3 = new Activity(2L, "Cook two egg", 20);
    }

    @Test
    public void checkConstructor() {
        assertNotNull(act1);
    }

    @Test
    public void testEquals() {

        assertTrue(act1.equals(act2));
    }

    @Test
    public void testEqualHashCode() {
        assertTrue(act1.hashCode() == act2.hashCode());
    }

    @Test
    public void testNotEquals() {

        assertNotEquals(act1, act3);
    }

    @Test
    public void testNotEqualHashCode() {

        assertNotEquals(act1, act3);
    }

    @Test
    public void testToString() {
        assertEquals(act1.toString(), "Activity{Id=1, text='Cook one egg', correctAnswer=10}");
    }
}
