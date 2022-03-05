package commons;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Activity {
    @Id
    private Long Id;//Int representing the activity's id
    private String text;//String representing the activity's text.
    private int correctAnswer;//Int representing the correct value of the activity's energy consumption
    //this will be implemented later
    //public Image image;

    /**
     *
     * @param id Int representing the activity's id
     * @param text String representing the activity's text.
     * @param correctAnswer Int representing the correct value of the activity's energy consumption.
     */
    public Activity(Long id, String text, int correctAnswer) {
        this.Id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    /**
     *
     * @param text String representing the activity's text.
     * @param correctAnswer Int representing the correct value of the activity's energy consumption
     */
    public Activity(String text, int correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public Activity() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Id == activity.Id && correctAnswer == activity.correctAnswer && text.equals(activity.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, text, correctAnswer);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "Id=" + Id +
                ", text='" + text + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
