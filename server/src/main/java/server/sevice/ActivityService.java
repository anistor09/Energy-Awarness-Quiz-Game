package server.sevice;

import commons.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.ActivityRepository;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

   
    
    public  List<Activity> getActivities() {return activityRepository.findAll();
    }

    public void addActivity(Activity activity) {activityRepository.save(activity);
    }

    public void deleteActivity(Long activityId) {
        boolean exists = activityRepository.existsById(activityId);
        if (!exists) {
            throw new IllegalStateException("activity with ID " + activityId + " does not exist.");
        }

        activityRepository.deleteById(activityId);
    }
}
