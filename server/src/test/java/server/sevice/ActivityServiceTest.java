package server.sevice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.database.ActivityRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;
    private ActivityService underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new ActivityService(activityRepository);
    }

    @Test
    void getActivities() {
        underTest.getActivities();
        verify(activityRepository).findAll();
    }

    @Test
    void addActivity() {
        underTest.addActivity(null);
        verify(activityRepository).save(null);
    }

    /**
     * This method is currently not testable due to the lack of activities on the database when test is ran.
     */
    @Disabled
    @Test
    void deleteActivity() {
    }

    @Test
    void getRandomActivity() {
        underTest.getRandomActivity();
        verify(activityRepository).findAll();
    }
}