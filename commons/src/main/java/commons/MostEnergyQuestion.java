package commons;

import java.util.ArrayList;
import java.util.Objects;

public class MostEnergyQuestion extends Question{
    private ArrayList<Activity> otherActivities;

    /**
     * Creates an instance of MostEnergyQuestion.
     * @param activity Activity to be used in the question.
     * @param difficulty      Difficulty of the question. This will determine the range of options that will be given
     *                        to the user.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     * @param allowedTime Maximum time allowed for this question.
     * @param otherActivities Other activities to be shown to the player.
     */
    public MostEnergyQuestion(Activity activity, int availablePoints, String difficulty, int allowedTime,
                              ArrayList<Activity> otherActivities) {
        super(activity, availablePoints, difficulty, allowedTime);
        this.otherActivities = otherActivities;
    }

    /**
     * Creates a new MostEnergyQuestion instance if no difficulty is provided. By default, the difficulty is "EASY".
     * @param activity Activity to be used in the question.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     * @param allowedTime Maximum time allowed for this question.
     */
    public MostEnergyQuestion(Activity activity, int availablePoints, int allowedTime,
                              ArrayList<Activity> otherActivities) {
        super(activity, availablePoints, allowedTime);
        this.otherActivities = otherActivities;
        this.setDifficulty("EASY");
    }

    /**
     * Ensures that this instance's activity has the most energy consumption.
     * @return true if this instance's activity has the most energy consumption, false otherwise.
     */
    public boolean checkValidity() {
        for (Activity activity : otherActivities) {
            if (activity.getConsumption_in_wh() > this.getActivity().getConsumption_in_wh()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Activity> getOtherActivities() {
        return otherActivities;
    }

    public void setOtherActivities(ArrayList<Activity> otherActivities) {
        this.otherActivities = otherActivities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MostEnergyQuestion)) return false;
        if (!super.equals(o)) return false;
        MostEnergyQuestion that = (MostEnergyQuestion) o;
        return getOtherActivities().equals(that.getOtherActivities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOtherActivities());
    }

    @Override
    public String toString() {
        return "MostEnergyQuestion{" +
                "otherActivities=" + otherActivities +
                '}';
    }
}
