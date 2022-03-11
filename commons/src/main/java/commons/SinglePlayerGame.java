package commons;

import java.util.ArrayList;
import java.util.Objects;

public class SinglePlayerGame extends Game{
    private Player player;

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
