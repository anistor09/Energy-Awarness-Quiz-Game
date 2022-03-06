package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EliminateOptionTest {

    EliminateOption eliminate;
    MultipleChoiceQuestion question;


    @BeforeEach
    void setUp() {
        Activity activity = new Activity();
        question = new MultipleChoiceQuestion(activity, 10, 20);
        eliminate = new EliminateOption("name", "desc", false, question);
    }

    @Test
    void useCard() {
        eliminate.useCard();
        assertTrue(question.getOptions().contains(question.getActivity().getCorrectAnswer()));
        assertEquals(2, question.getOptions().size());
    }

    @Test
    void testEquals() {
        assertFalse(eliminate.equals(question));
    }

    @Test
    void testHashCode() {
        assertFalse(question.hashCode() == eliminate.hashCode());
    }


}