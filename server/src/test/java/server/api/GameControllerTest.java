package server.api;

import commons.GuessQuestion;
import commons.InsteadOfQuestion;
import commons.MostEnergyQuestion;
import commons.MultipleChoiceQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.sevice.GameService;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @Mock private GameService gameService;
    private GameController underTest;

    @BeforeEach
    void setUp() {
        underTest = new GameController(gameService);
    }

    @Test
    void getListMostEnergy() {
        List<MostEnergyQuestion> q = underTest.getListMostEnergy();
        verify(gameService).getListMostEnergy();
    }

    @Test
    void getListGuess() {
        List<GuessQuestion> q = underTest.getListGuess();
        verify(gameService).getListGuessQuestion();
    }

    @Test
    void getListMultipleChoice() {
        List<MultipleChoiceQuestion> q = underTest.getListMultipleChoice();
        verify(gameService).getListMultipleChoice();
    }

    @Test
    void getListInsteadOf() {
        List<InsteadOfQuestion> q = underTest.getListInsteadOf();
        verify(gameService).getListInsteadOf();
    }
}