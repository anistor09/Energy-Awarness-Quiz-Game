package server.sevice;

import commons.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.api.ActivityController;

import java.util.ArrayList;

@Service
public class QuestionService {

    private final ActivityController activityController;

    @Autowired
    public QuestionService(ActivityController activityController) {
        this.activityController = activityController;
    }

    /**
     * This method will prepare a random question for the game. It will first make a randomization of question types.
     * @return the random question
     */
    public Question getRandomQuestion() {
        int questionType = (int) (Math.random() * 3);
        switch (questionType) {
            case 0:
                return getRandomMultipleChoiceQuestion();
            case 1:
                return getRandomGuessQuestion();
            case 2:
                return getRandomInsteadOfQuestion();
            default:
                return getRandomMostEnergyQuestion();
        }
    }

    /**
     * This method will get a random MultipleChoiceQuestion. It will request a random activity from the database through
     * the activityController
     * @return the MultipleChoiceQuestion
     */
    public Question getRandomMultipleChoiceQuestion() {
        Activity activity = activityController.getRandomActivity();
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(activity, 100, 30);
        return multipleChoiceQuestion;
    }

    /**
     * This method will get a random GuessQuestion. It will request a random activity from the database through the
     * activityController
     * @return the GuessQuestion
     */
    public Question getRandomGuessQuestion() {
        Activity activity = activityController.getRandomActivity();
        GuessQuestion guessQuestion = new GuessQuestion(activity, 100, 30);
        return guessQuestion;
    }

    /**
     * This method will get a random InsteadOfQuestion. It will request the main activity from the database through the
     * activityController and then will request the other activities (also random) to prepare the options. The ratio
     * is done in the InsteadOfQuestion class
     * @return the InsteadOfQuestion
     */
    public Question getRandomInsteadOfQuestion() {
        Activity activity = activityController.getRandomActivity();
        ArrayList<Activity> options = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Activity option = activityController.getRandomActivity();
            if (options.contains(option) || option.equals(activity)) {
                i--;
                continue;
            } else {
                options.add(option);
            }
        }
        InsteadOfQuestion insteadOfQuestion = new InsteadOfQuestion(activity, 100, 30, options);
        return insteadOfQuestion;
    }

    /**
     * This method will get a random MostEnergyQuestion. It wil request the main activity from the database through
     * the activityController and then will request the other activities (also random) to prepare the options
     * @return the MostEnergyQuestion
     */
    public Question getRandomMostEnergyQuestion() {
        Activity activity = null;
        ArrayList<Activity> options = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Activity option = activityController.getRandomActivity();
            if (options.contains(option)) {
                i--;
                continue;
            } else {
                options.add(option);
            }
        }
        return new MostEnergyQuestion(activity, 100, 30, options);
    }
}
