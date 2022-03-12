package commons;

import java.util.ArrayList;
import java.util.Objects;

public class InsteadOfQuestion extends Question{


    private ArrayList<Activity> options;

    /**
     * Creates an instance of InsteadOfQuestion.
     * @param activity Activity to be used in the question.
     * @param difficulty      Difficulty of the question. This will determine the range of options that will be given
     *                        to the user.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     * @param allowedTime Maximum time allowed for this question.
     * @param options Other activities shown to the player.
     */
    public InsteadOfQuestion(Activity activity, int availablePoints, String difficulty, int allowedTime,
                             ArrayList<Activity> options) {
        super(activity, availablePoints, difficulty, allowedTime);
        this.options = options;
    }

    /**
     * Creates a new InsteadOfQuestion instance if no difficulty is provided. By default, the difficulty is "EASY".
     * @param activity Activity to be used in the question.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     * @param allowedTime Maximum time allowed for this question.
     * @param options Other activities shown to the player;
     */
    public InsteadOfQuestion(Activity activity, int availablePoints, int allowedTime, ArrayList<Activity> options) {
        super(activity, availablePoints, allowedTime);
        this.options = options;
    }

    /**
     * Compares this activity with another activity.
     * @param other Activity to be compared with.
     * @return int How many times the second activity can be done using the same consumption as this activity.
     */
    public int compareActivities (Activity other) {
        int thisConsumption = this.getActivity().getCorrectAnswer();
        int otherConsumption = other.getCorrectAnswer();

        return otherConsumption/thisConsumption;
    }

    /**
     * Compares and returns a String representing how many times another activity can be done using the same
     * consumption as this activity.
     * @param other Activity to be compared with.
     * @return String representing how many times another activity can be done using the same consumption as this
     * activity.
     */
    public String substituteActivity(Activity other) {
        return "Instead of \n" + this.getActivity().getText() + "\nYou could \n" + other.getText() +
                "\n" + compareActivities(other) + " times";
    }

    @Override
    public String toString() {
        return "InsteadOfQuestion{}";
    }

    public ArrayList<Activity> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Activity> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsteadOfQuestion)) return false;
        if (!super.equals(o)) return false;
        InsteadOfQuestion that = (InsteadOfQuestion) o;
        return getOptions().equals(that.getOptions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOptions());
    }
}
