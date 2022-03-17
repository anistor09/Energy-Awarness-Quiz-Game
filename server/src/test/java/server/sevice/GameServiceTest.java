package server.sevice;

import commons.Question;
import commons.SinglePlayerGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.api.QuestionController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock private QuestionController questionController;
    private GameService underTest;

    @BeforeEach
    void setUp() {
        underTest = new GameService(questionController);
    }

    /**
     * This method is currently not testable due to the lack of activities on the database when test is ran.
     */
    @Disabled
    @Test
    void createGame() {
        SinglePlayerGame game = underTest.createGame();
        verify(questionController).getRandomQuestion();
        assertEquals(21, game.getQuestions().size());
        assertFalse(containsDuplicates(game.getQuestions()));
    }

    boolean containsDuplicates(List<Question> list) {
        Set<Question> set = new HashSet<>();
        set.addAll(list);
        if(set.size() != list.size()) return true;
        return false;
    }
}