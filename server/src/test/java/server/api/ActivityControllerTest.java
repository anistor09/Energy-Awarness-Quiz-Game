package server.api;

import commons.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.database.ActivityRepositoryTest;
import server.sevice.ActivityService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class ActivityControllerTest {
   private ActivityRepositoryTest myRepo;
   private ActivityController controller;
    @BeforeEach
    public void setup() {

        myRepo = new ActivityRepositoryTest();
        controller = new ActivityController(new ActivityService(myRepo));
    }

    @Test
    public void databaseIsUSed() {
        controller.addNewActivity(getActivity());
        myRepo.calledMethods.contains("save");
    }
    @Test
    public void testGetActivities(){
        controller.addNewActivity(getActivity());
        controller.addNewActivity(new Activity(2L,"boil twp eggs",20));
        List<Activity> localList  = List.of(getActivity(), new Activity(2L,"boil twp eggs",20));
        List<Activity> repositoryList = controller.getActivities();
        assertEquals(localList,repositoryList);
    }

    @Test
    public void testDeleteActivities(){
        controller.addNewActivity(getActivity());
        List<Activity> localList  = new ArrayList<>();
        controller.deleteActivity(1L);
        List<Activity> repositoryList = controller.getActivities();
        assertEquals(localList,repositoryList);
    }
    public static Activity getActivity(){
        return new Activity(1L,"boil one egg",10);
    }

}