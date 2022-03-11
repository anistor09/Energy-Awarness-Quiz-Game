package commons;

public class InsteadOfQuestion extends Question{

    public InsteadOfQuestion(Activity activity, int availablePoints, String difficulty, int allowedTime) {
        super(activity, availablePoints, difficulty, allowedTime);
    }

    public InsteadOfQuestion(Activity activity, int availablePoints, int allowedTime) {
        super(activity, availablePoints, allowedTime);
    }

    public int compareActivities (Activity other) {
        int thisConsumption = this.getActivity().getCorrectAnswer();
        int otherConsumption = other.getCorrectAnswer();

        return otherConsumption/thisConsumption;
    }

    public String substituteActivity(Activity other) {
        return "Instead of \n" + this.getActivity().getText() + "\nYou could \n" + other.getText() +
                "\n" + compareActivities(other) + " times";
    }

    @Override
    public String toString() {
        return "InsteadOfQuestion{}";
    }
}
