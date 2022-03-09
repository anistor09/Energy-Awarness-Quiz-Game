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
        Activity activity = new Activity(1L, "Cook one egg", 10);
        question = new MultipleChoiceQuestion(activity, 10,"EASY", 20);
        eliminate = new EliminateOption("name", "desc", false, question);
    }

    @Test
    void useCard() {
        eliminate.useCard();
        assertTrue(question.getOptions().contains((double) question.getActivity().getCorrectAnswer()));
        assertEquals(2, question.getOptions().size());
    }

}