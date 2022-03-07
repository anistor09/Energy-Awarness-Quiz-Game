package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExtendTimeTest {
    ExtendTime extend;
    ExtendTime extend1;

    @BeforeEach
    public void setup(){
        extend = new ExtendTime("10 seconds extend card",
                "Gives you 10sec more time to answer this question",
                false, 10);
        extend1 = new ExtendTime("Another name",
                "Gives you 50% more time to answer this question",
                false, 10);
    }


    @Test
    void constructorTest(){
        assertNotNull(extend);
    }
    @Test
    void testEquals() {
        assertNotEquals(extend, extend1);
    }

    @Test
    void testHashCode() {
        ExtendTime extend2 = new ExtendTime("10 seconds extend card",
                "Gives you 10sec more time to answer this question",
                false, 10);
        assertTrue(extend.hashCode() == extend2.hashCode());
    }

//    this needs Som's  updated Question class
//    @Test
//    void useCard() {
//    }

    @Test
    void getAdditionalTime() {
        assertEquals(10, extend.getAdditionalTime());
    }

    @Test
    void setAdditionalTime() {
        extend1.setAdditionalTime(5);
        assertEquals(5, extend1.getAdditionalTime());
    }

    @Test
    void testToString() {
        assertEquals(extend.toString(), "ExtendTime{additionalTime=10}");
    }
}