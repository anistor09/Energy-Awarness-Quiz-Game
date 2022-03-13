package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExtendTimeTest {
    ExtendTime extend;
    Question question;

    @BeforeEach
    void setUp() {
        Activity activity = new Activity("00-shower",
                "00/shower.png",
                "Taking a hot shower for 6 minutes",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        question = new MultipleChoiceQuestion(activity, 10, "EASY", 20);
        extend = new ExtendTime("10 seconds extend card",
                "Gives you 10sec more time to answer this question",
                false, 10, question);
    }

    @Test
    void useCard() {
        extend.useCard();
        assertEquals(question.getAllowedTime(), 30);
    }

}