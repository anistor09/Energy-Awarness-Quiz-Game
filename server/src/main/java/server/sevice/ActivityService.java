package server.sevice;

import commons.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.ActivityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    /**
     *  Creates an instance of this class ActivityService Class.
     * @param activityRepository   An instance of the repository class.
     */
    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }


    /**
     * Returns all the activities from the database.
     * @return List of Player instances found the in the repository.
     */
    public  List<Activity> getActivities() {return activityRepository.findAll();
    }

    /**
     * Service layer method for adding an activity to the repository.
     * @param activity An instance of the Activity Class  that will be added to the repository;
     */
    public void addActivity(Activity activity) {activityRepository.save(activity);
    }

    /**
     *  Service layer method for deleting a player from the repository.
     * @param activityId Unique ID of the activity to be deleted.
     */
    public void deleteActivity(String activityId) {
        boolean exists = activityRepository.existsById(activityId);
        if (!exists) {
            throw new IllegalStateException("activity with ID " + activityId + " does not exist.");
        }

        activityRepository.deleteById(activityId);
    }

    public Activity getActivityById(String id) {
        Optional<Activity> activity = activityRepository.findById(id);
        if(activity.isPresent()) {
            return activity.get();
        } else {
            throw new IllegalStateException("activity with ID " + id + " does not exist.");
        }
    }

    /**
     * This method will pick a random activity from the database. It currently retrieves all and then picks a random
     * one. Maybe there is a better way to do this
     * @return the random Activity
     */
    public Activity getRandomActivity() {
        List<Activity> list = activityRepository.findAll();
        if(list.size() == 0) return null;
        int random = (int) (Math.random() * list.size());
        return list.get(random);
    }
}
