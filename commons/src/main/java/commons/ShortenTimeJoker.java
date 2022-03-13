package commons;

import java.util.Objects;

public class ShortenTimeJoker extends JokerCard{
    private static final boolean onlyMultiplayer = true;
    private int additionalTime;
    private Question question;
    //private Game game;


    public ShortenTimeJoker(String name, String description, int additionalTime, Question question) {
        super(name, description, onlyMultiplayer);
        this.additionalTime = additionalTime;
        this.question = question;
    }

    /**
     * This card is meant for solely multiplayer Games.
     * Game class is needed to access another players' times.
     */
    @Override
    public void useCard() {
        int newTime;
        newTime = (int) (0.75 * question.getAllowedTime());
        //this.question.setAllowedTime(newTime);
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
        ShortenTimeJoker that = (ShortenTimeJoker) o;
        return additionalTime == that.additionalTime && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), additionalTime, question);
    }

    @Override
    public String toString() {
        return "ShortenTimeJoker{" +
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
