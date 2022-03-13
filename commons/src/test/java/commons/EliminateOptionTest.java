package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EliminateOptionTest {

    EliminateOption eliminate;
    MultipleChoiceQuestion question;


    @BeforeEach
    void setUp() {
        Activity activity = new Activity("00-shower",
                "00/shower.png",
                "Taking a hot shower for 6 minutes",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
        question = new MultipleChoiceQuestion(activity, 10,"EASY", 20);
        eliminate = new EliminateOption("name", "desc", false, question);
    }

    @Test
    void useCard() {
        eliminate.useCard();
        assertTrue(question.getOptions().contains((double) question.getActivity().getConsumption_in_wh()));
        assertEquals(2, question.getOptions().size());
    }

}