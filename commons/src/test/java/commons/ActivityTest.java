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

    /**
     * Initialising 3 random activities in order to test the features , the first 2 ones have the sam attributes
     * and the third one is different.
     */
    @BeforeEach
    public void setup() {
        act1 = new Activity("00-shower",
                "00/shower.png",
                "Taking a hot shower for 6 minutes",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        act2 =new Activity("00-shower",
                "00/shower.png",
                "Taking a hot shower for 6 minutes",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        act3 = new Activity("00-smartphone",
                "00/smartphone.png",
                "Charging your smartphone at night",
                10,
                "https://9to5mac.com/2021/09/16/iphone-13-battery-life/");
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
        assertEquals(act1.toString(), "Activity{id='00-shower'," +
                " image_path='00/shower.png', " +
                "title='Taking a hot shower for 6 minutes'," +
                " consumption_in_wh=4000," +
                " source='https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower'}");
    }
}
