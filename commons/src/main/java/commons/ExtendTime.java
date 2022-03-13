package commons;

import java.util.Objects;

public class ExtendTime extends JokerCard{
    private int additionalTime;
    private Question question;


    public ExtendTime(String name, String description, boolean onlyMultiplayer, int additionalTime, Question question) {
        super(name, description, onlyMultiplayer);
        this.additionalTime = additionalTime;
        this.question = question;
    }

    @Override
    public void useCard() {
        int newTime;
        newTime = (int) (1.5 * question.getAllowedTime());
        this.question.setAllowedTime(newTime);
    }

    public int getAdditionalTime() {
        return additionalTime;
    }

    public void setAdditionalTime(int additionalTime) {
        this.additionalTime = additionalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExtendTime that = (ExtendTime) o;
        return additionalTime == that.additionalTime && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), additionalTime, question);
    }

    @Override
    public String toString() {
        return "ExtendTime{" +
                "additionalTime=" + additionalTime +
                '}';
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
