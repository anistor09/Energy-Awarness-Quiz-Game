package commons;

import java.util.Objects;

public class AdditionalPoints extends JokerCard{

    private int additionalPoints;
    private Player player;

    public AdditionalPoints(String name,
                            String description,
                            boolean onlyMultiplayer,
                            int additionalPoints,
                            Player player) {
        super(name, description, onlyMultiplayer);
        this.additionalPoints = additionalPoints;
        this.player = player;
    }


    public int getAdditionalPoints() {
        return additionalPoints;
    }

    public void setAdditionalPoints(int additionalPoints) {
        this.additionalPoints = additionalPoints;
    }

    @Override
    public void useCard() {
        this.player.setCurrentScore(player.getCurrentScore() + 10);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AdditionalPoints that = (AdditionalPoints) o;
        return additionalPoints == that.additionalPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), additionalPoints);
    }

    @Override
    public String toString() {
        return "AdditionalPoints{" +
                "additionalPoints=" + additionalPoints +
                '}';
    }
}
