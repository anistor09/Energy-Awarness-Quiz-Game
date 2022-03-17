package server.sevice;

import commons.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import server.api.ActivityController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private ActivityController activityController;
    private QuestionService underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new QuestionService(activityController);
    }

    @Test
    void getRandomMultipleChoiceQuestion() {
        Mockito.when(activityController.getRandomActivity()).thenReturn(getActivity());
        underTest.getRandomMultipleChoiceQuestion();
        verify(activityController).getRandomActivity();
    }

    @Test
    void getRandomGuessQuestion() {
        Mockito.when(activityController.getRandomActivity()).thenReturn(getActivity());
        underTest.getRandomGuessQuestion();
        verify(activityController).getRandomActivity();
    }

    /**
     * This method is currently not testable due to the lack of activities on the database when test is ran.
     */
    @Disabled
    @Test
    void getRandomInsteadOfQuestion() {
    }

    /**
     * This method is currently not testable due to the lack of activities on the database when test is ran.
     */
    @Disabled
    @Test
    void getRandomMostEnergyQuestion() {
    }

    public static Activity getActivity(){
        return new Activity("00-shower",
                "00/shower.png",
                "Taking a hot shower for 6 minutes",
                4000,
                "https://www.quora.com/How-can-I-estimate-the-kWh-of-electricity-when-I-take-a-shower");
    }
}