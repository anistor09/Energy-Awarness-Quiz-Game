package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;



class PlayerTest {
    Player player1;
    Player player2;
    Player player3;
    @BeforeEach
    public void setup() {
       player1 = new Player(1L,"rafael1234",1256);
        player2 = new Player(1L,"rafael1234",1256);
        player3 = new Player(2L,"alex1234",1300);
    }
    @Test
    public void checkConstructor() {
        assertNotNull(player1);
    }
    @Test
    public void testEquals() {

        assertTrue(player1.equals(player2));
    }
    @Test
    public void testEqualHashCode() {
        assertTrue(player1.hashCode() == player2.hashCode());
    }
    @Test
    public void testNotEquals() {

        assertNotEquals(player1, player3);
    }

    @Test
    public void testNotEqualHashCode() {

        assertNotEquals(player1, player3);
    }

    @Test
    public void testToString() {
        assertEquals(player1.toString(), "Player{id=1, username='rafael1234', currentScore=1256}");
    }
}



