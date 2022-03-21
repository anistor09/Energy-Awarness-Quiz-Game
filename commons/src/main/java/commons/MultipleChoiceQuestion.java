package commons;

import java.util.ArrayList;
import java.util.Objects;

public class MultipleChoiceQuestion extends Question{

    private ArrayList<Double> options;
    private double correctAnswer;


    /**
     * Creates an instance of MultipleChoiceQuestion.
     * @param activity Activity to be used in the question.
     * @param difficulty      Difficulty of the question. This will determine the range of options that will be given
     *                        to the user.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     * @param allowedTime Maximum time allowed for this question.
     */
    public MultipleChoiceQuestion(Activity activity, int availablePoints, String difficulty, int allowedTime) {
        super(activity, availablePoints, difficulty, allowedTime);


        // create a range of answers
        double correctAnswer = activity.getConsumption_in_wh();
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
        this.correctAnswer = (long) correctAnswer;
        this.options = options;
    }

    /**
     * Creates a new MultipleChoiceQuestion instance if no difficulty is provided. By default, the difficulty is "EASY".
     * @param activity Activity to be used in the question.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     * @param allowedTime Maximum time allowed for this question.
     */
    public MultipleChoiceQuestion(Activity activity, int availablePoints, int allowedTime) {
        super(activity, availablePoints, allowedTime);
        this.setDifficulty("EASY");
        // create a range of answers
        double correctAnswer = activity.getConsumption_in_wh();
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
        optionOne = (long)((Math.random() * range) + lowerBound);
        optionTwo = optionOne;
        while (optionOne == optionTwo) {
            optionTwo = (long)((Math.random() * range) + lowerBound);
        }

        ArrayList<Double> returnable = new ArrayList<>();
        returnable.add(optionOne);
        returnable.add(optionTwo);
        
        return returnable;
    }


    public ArrayList<Double> getOptions() {
        return options;
    }


    /**
     * Equals method between two instances of the MultipleChoiceQuestion class, not including the ArrayList options
     * as this is
     * automatically generated.
     * @param o Object to be compared with
     * @return TRUE if objects have equal attributes, FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultipleChoiceQuestion that = (MultipleChoiceQuestion) o;
        return Objects.equals(options, that.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(options);
    }

    public double getCorrectAnswer() {
        return correctAnswer;
    }
}



