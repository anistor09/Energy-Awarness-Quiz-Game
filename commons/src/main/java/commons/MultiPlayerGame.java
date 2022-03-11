package commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MultiPlayerGame extends Game{
    private ArrayList<Player> players;
    private HashMap<Player, Integer> leaderboard;   // live leaderboard

    public MultiPlayerGame(ArrayList<Question> questions, ArrayList<JokerCard> jokerCards) {
        super(questions, jokerCards);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public HashMap<Player, Integer> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(HashMap<Player, Integer> leaderboard) {
        this.leaderboard = leaderboard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultiPlayerGame)) return false;
        if (!super.equals(o)) return false;
        MultiPlayerGame that = (MultiPlayerGame) o;
        return getPlayers().equals(that.getPlayers()) && getLeaderboard().equals(that.getLeaderboard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPlayers(), getLeaderboard());
    }

    @Override
    public String toString() {
        return "MultiPlayerGame{" +
                "players=" + players +
                ", leaderboard=" + leaderboard +
                '}';
    }
}
