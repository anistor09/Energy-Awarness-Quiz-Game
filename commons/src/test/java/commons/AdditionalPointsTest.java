package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalPointsTest {

    AdditionalPoints additionalPoints1;
    AdditionalPoints additionalPoints2;

    @BeforeEach
    public void setup(){
        additionalPoints1 = new AdditionalPoints("Additional",
                "Adds 10 additional points if you answer correctly",
                false,
                10);
        additionalPoints2 = new AdditionalPoints("Additional",
                "Adds 10 additional points if you answer correctly",
                false,
                10);

    }

    @Test
    void getAdditionalPoints() {
        assertEquals(10, additionalPoints1.getAdditionalPoints());
    }

    @Test
    void setAdditionalPoints() {
        additionalPoints1.setAdditionalPoints(15);
        assertEquals(15, additionalPoints1.getAdditionalPoints());
    }

    @Test
    void useCard() {
        Player player = new Player("noname", 17);
        additionalPoints1.useCard(player);
        assertEquals(27, player.getCurrentScore());

    }

    @Test
    void testEquals() {
        assertEquals(additionalPoints1, additionalPoints2);
    }

    @Test
    void testNotEquals() {
        AdditionalPoints additionalPoints3 = new AdditionalPoints("Additional",
                "Adds 10 additional points if you answer correctly",
                false,
                20);
        assertNotEquals(additionalPoints1, additionalPoints3);
    }

    @Test
    void testNullEquals() {
        AdditionalPoints additionalPoints3 = null;
        assertNotEquals(additionalPoints1, additionalPoints3);
    }

    @Test
    void testHashCode() {
        assertTrue(additionalPoints1.hashCode() == additionalPoints2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(additionalPoints1.toString(), "AdditionalPoints{additionalPoints=10}");
    }
}