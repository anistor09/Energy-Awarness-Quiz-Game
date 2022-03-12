package commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MultiPlayerGame extends Game{
    private ArrayList<Player> players;
    private HashMap<Player, Integer> leaderboard;   // live leaderboard

    /**
     *
     * @param questions Questions to be answered by players in this game.
     * @param jokerCards Joker cards to be made available to players in the game.
     * @param players Players playing in the game.
     * @param leaderboard Live leaderboard showing each player and their current score.
     */
    public MultiPlayerGame(ArrayList<Question> questions, ArrayList<JokerCard> jokerCards, ArrayList<Player> players,
                           HashMap<Player, Integer> leaderboard) {
        super(questions, jokerCards);
        this.players = players;
        this.leaderboard = leaderboard;
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
