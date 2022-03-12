package commons;

public class GuessQuestion extends Question{


    /**
     * Creates an instance of GuessQuestion.
     * @param activity Activity to be used in the question.
     * @param difficulty      Difficulty of the question. This will determine the range of options that will be given
     *                        to the user.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     * @param allowedTime Maximum time allowed for this question.
     */
    public GuessQuestion(Activity activity, int availablePoints, String difficulty, int allowedTime) {
        super(activity, availablePoints, difficulty, allowedTime);
    }

    /**
     * Creates a new GuessQuestion instance if no difficulty is provided. By default, the difficulty is "EASY".
     * @param activity Activity to be used in the question.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     * @param allowedTime Maximum time allowed for this question.
     */
    public GuessQuestion(Activity activity, int availablePoints, int allowedTime) {
        super(activity, availablePoints, allowedTime);
        this.setDifficulty("EASY");
    }

    /**
     * Returns the closeness of guess to the actual energy consumption of the activity as a value between 0 and 1. If
     * the returned value is 1, the guess is exactly equal to the correct answer.
     * @param guess Number guessed by the player.
     * @return Closeness of the guess to the correct answer.
     */
    public double calculateCloseness(double guess) {
        double correctAnswer = this.getActivity().getCorrectAnswer();
        // Formula: if the guess is 100% away from the actual answer, the closeness should be 0. Else, it is 1-error

        if (guess >= 2*correctAnswer || guess <= 0) {
            return 0;
        }
        return 1-(Math.abs(guess-correctAnswer))/correctAnswer;
    }


}
