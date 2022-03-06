package server.api;

import commons.Activity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import server.sevice.ActivityService;

import java.util.List;

@RestController
@RequestMapping("api/activity")
public class ActivityController {
    /**
     * API layer of the Activity class.
     */
    private final ActivityService activityService;

    /**
     * Creates an instance of ActivityController Class
     * @param activityService    An instance of the utility class that links the API Layer to the Repository Layer
     */
    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * Serves the user's GET request by invoking the getActivities() method from the activityService object
     * @return List of activities returned by the service layer.
     */
    @GetMapping
    public List<Activity> getActivities(){
        return activityService.getActivities();
    }

    /**
     *  API layer method for the POST request
     * @param activity An instance of the utility class that links the API Layer to the Repository Layer
     */
    @PostMapping
    public void addNewActivity( @RequestBody Activity activity){ activityService.addActivity(activity);
    }

    /**
     *  API layer method for DELETE request.
     * @param activityId ID of the activity to be deleted.
     */
    @DeleteMapping(path="{activityId}")
    public void deleteActivity(@PathVariable("activityId") Long activityId) {
        activityService.deleteActivity(activityId);
    }
}
