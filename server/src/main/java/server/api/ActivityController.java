package server.api;

import commons.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.sevice.ActivityService;

import java.util.List;

@RestController
@RequestMapping("api/activity")
public class ActivityController {

    private final ActivityService activityService;
    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }


    @GetMapping
    public List<Activity> getActivities(){
        return activityService.getActivities();
    }


    @PostMapping
    public void addNewActivity( @RequestBody Activity activity){ activityService.addActivity(activity);
    }


    @DeleteMapping(path="{activityId}")
    public void deleteActivity(@PathVariable("activityId") Long activityId) {
        activityService.deleteActivity(activityId);
    }
}
