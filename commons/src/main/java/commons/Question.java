package commons;

import java.util.ArrayList;
import java.util.Objects;

public class Question {

    private Activity activity;
    private int availablePoints;
    private String difficulty;
    private ArrayList<Double> options;

    /**
     * Creates an instance of Question.
     * @param activity Activity to be used in the question.
     * @param difficulty      Difficulty of the question. This will determine the range of options that will be given
     *                        to the user.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     */
    public Question(Activity activity, int availablePoints, String difficulty) {
        this.activity = activity;
        this.availablePoints = availablePoints;
        this.difficulty = difficulty;

        // create a range of answers
        double correctAnswer = activity.getCorrectAnswer();
        ArrayList<Double> options;
        switch (difficulty){
            case "EASY":
                options = generateRandomNumbers(correctAnswer*0.8, correctAnswer*1.2);
                break;
            case "MEDIUM":
                options = generateRandomNumbers(correctAnswer*0.9, correctAnswer*1.1);
                break;
            case "HARD":
                options = generateRandomNumbers(correctAnswer*0.95, correctAnswer*1.05);
                break;
            default:
                throw new IllegalArgumentException("You did not specify a valid difficulty");
        }


        options.add(correctAnswer);

        this.options = options;
    }

    /**
     * Creates a new Question instance if no difficulty is provided. By default, the difficulty is "EASY".
     * @param activity Activity to be used in the question.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     */
    public Question(Activity activity, int availablePoints) {
        this.activity = activity;
        this.availablePoints = availablePoints;
        this.difficulty = "EASY";

        // create a range of answers
        double correctAnswer = activity.getCorrectAnswer();
        ArrayList<Double> options = generateRandomNumbers(correctAnswer * 0.8,
                correctAnswer * 1.2);

        options.add(correctAnswer);
        this.options = options;
    }

    /**
     * Private utility method used by the constructor.
     *
     * @param lowerBound Lower bound.
     * @param upperBound Upper bound.
     * @return An ArrayList of two distinct random numbers between lowerBound and upperBound.
     */
    private ArrayList<Double> generateRandomNumbers(double lowerBound, double upperBound) {
        double range = upperBound - lowerBound;
        double optionOne;
        double optionTwo;

        // generate 2 unique numbers within these bounds
        optionOne = ((Math.random() * range) + lowerBound);
        optionTwo = optionOne;
        while (optionOne == optionTwo) {
            optionTwo = (int)((Math.random() * range) + lowerBound);
        }

        ArrayList<Double> returnable = new ArrayList<>();
        returnable.add(optionOne);
        returnable.add(optionTwo);
        
        return returnable;
    }

    public Activity getActivity() {
        return activity;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public ArrayList<Double> getOptions() {
        return options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        boolean equalPoints = (getAvailablePoints() == question.getAvailablePoints());
        boolean equalActivities = (getActivity().equals(question.getActivity()));
        boolean equalDifficulties = (getDifficulty().equals(question.getDifficulty()));

        return getAvailablePoints() == question.getAvailablePoints() && getActivity().equals(question.getActivity())
                && getDifficulty().equals(question.getDifficulty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActivity(), getAvailablePoints(), getDifficulty());
    }
}



