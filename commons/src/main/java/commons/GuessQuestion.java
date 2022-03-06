package commons;

public class GuessQuestion extends Question{
    private Activity activity;
    private int availablePoints;
    private String difficulty;
    private int allowedTime;

    public GuessQuestion(Activity activity, int availablePoints, String difficulty, int allowedTime) {
        super(activity, availablePoints, difficulty, allowedTime);
    }

    public GuessQuestion(Activity activity, int availablePoints, int allowedTime) {
        super(activity, availablePoints, allowedTime);
        this.difficulty = "EASY";
    }

    /**
     * Returns the closeness of guess to the actual energy consumption of the activity as a value between 0 and 1. If
     * the returned value is 1, the guess is exactly equal to the correct answer.
     * @param guess Number guessed by the player.
     * @return Closeness of the guess to the correct answer.
     */
    public double calculateCloseness(double guess) {
        double correctAnswer = this.activity.getCorrectAnswer();
        // Formula: if the guess is 100% away from the actual answer, the closeness should be 0. Else, it is 1-error

        if (guess >= 2*correctAnswer || guess <= 0) {
            return 0;
        }
        return 1-(Math.abs(guess-correctAnswer))/correctAnswer;
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

    public int getAllowedTime() {
        return allowedTime;
    }
}
