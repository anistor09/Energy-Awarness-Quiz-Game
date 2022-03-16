package server.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.sevice.QuestionService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

    @Mock private QuestionService questionService;
    private QuestionController underTest;

    @BeforeEach
    void setUp() {
        underTest = new QuestionController(questionService);
    }

    @Test
    void getRandomQuestion() {
        underTest.getRandomQuestion();
        verify(questionService).getRandomQuestion();
    }
}