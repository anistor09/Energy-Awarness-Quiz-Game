package commons;

import java.util.ArrayList;
import java.util.Objects;

public class SinglePlayerGame extends Game{
    private Player player;

    /**
     * Creates an instance of a single-player game.
     * @param questions Questions to be answered by the player in this game.
     * @param jokerCards Joker cards to be made available to players in the game.
     * @param player Player participating in this game.
     */
    public SinglePlayerGame(ArrayList<Question> questions, ArrayList<JokerCard> jokerCards, Player player) {
        super(questions, jokerCards);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SinglePlayerGame)) return false;
        if (!super.equals(o)) return false;
        SinglePlayerGame that = (SinglePlayerGame) o;
        return getPlayer().equals(that.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPlayer());
    }

    @Override
    public String toString() {
        return "SinglePlayerGame{" +
                "player=" + player +
                '}';
    }
}
