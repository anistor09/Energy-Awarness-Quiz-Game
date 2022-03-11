package commons;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Game {
    private ArrayList<Question> questions;  // this is the list of questions that players will be able to view throughout the game
    private ArrayList<JokerCard> jokerCards;    // the list of all jokers that are available

    public Game(ArrayList<Question> questions, ArrayList<JokerCard> jokerCards) {
        this.questions = questions;
        this.jokerCards = jokerCards;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public ArrayList<JokerCard> getJokerCards() {
        return jokerCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(getQuestions(), game.getQuestions()) && Objects.equals(getJokerCards(), game.getJokerCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestions(), getJokerCards());
    }

    @Override
    public String toString() {
        return "Game{" +
                "questions=" + questions +
                ", jokerCards=" + jokerCards +
                '}';
    }
}
