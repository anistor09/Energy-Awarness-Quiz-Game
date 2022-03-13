package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShortenTimeJokerTest {
    ShortenTimeJoker extend;
    Question question;

    @BeforeEach
    void setUp() {
        Activity activity = new Activity("00-shower",
                "00/shower.png",
                "Taking a hot shower for 6 minutes",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        question = new MultipleChoiceQuestion(activity, 10, "EASY", 20);
        extend = new ShortenTimeJoker("10 seconds extend card",
                "Gives you 10sec more time to answer this question", 10, question);
    }


    @Test
    void useCard() {
    }

    @Test
    void getAdditionalTime() {
        assertEquals(question, extend.getQuestion());
    }


    @Test
    void testEquals() {
        ShortenTimeJoker extend2 = new ShortenTimeJoker("10 seconds extend card",
                "Gives you 10sec more time to answer this question", 10, question);
        assertTrue(extend2.equals(extend));
    }

    @Test
    void testNotEquals() {
        ShortenTimeJoker extend2 = new ShortenTimeJoker("10 seconds extend card different name",
                "Different description", 50, question);
        assertNotEquals(extend2, extend);
    }

    @Test
    void testHashCode() {
        ShortenTimeJoker extend2 = new ShortenTimeJoker("10 seconds extend card",
                "Gives you 10sec more time to answer this question", 10, question);
        assertTrue(extend.hashCode() == extend2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(extend.toString(), "ShortenTimeJoker{additionalTime=10}");
    }

    @Test
    void getQuestion() {
        assertEquals(question, extend.getQuestion());
    }

}