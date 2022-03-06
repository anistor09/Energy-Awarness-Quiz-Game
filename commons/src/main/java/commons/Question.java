package commons;

public abstract class Question {

    private Activity activity;
    private int availablePoints;
    private String difficulty;
    private int allowedTime;    // maximum allowed time for this question

    public Question(Activity activity, int availablePoints, String difficulty, int allowedTime) {
        this.activity = activity;
        this.availablePoints = availablePoints;
        this.difficulty = difficulty;
        this.allowedTime = allowedTime;
    }

    public Question(Activity activity, int availablePoints, int allowedTime) {
        this.activity = activity;
        this.availablePoints = availablePoints;
        this.allowedTime = allowedTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getAllowedTime() {
        return allowedTime;
    }

    public void setAllowedTime(int allowedTime) {
        this.allowedTime = allowedTime;
    }
}
