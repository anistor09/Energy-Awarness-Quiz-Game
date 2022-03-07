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


}
