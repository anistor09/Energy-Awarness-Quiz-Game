package commons;

import java.util.Objects;

public class EliminateOption extends JokerCard{
    private double correctOption;

    public EliminateOption(String name, String description, boolean onlyMultiplayer) {
        super(name, description, onlyMultiplayer);
    }

    @Override
    public void useCard(Player player) {

    }

    public double getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(double correctOption) {
        this.correctOption = correctOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EliminateOption that = (EliminateOption) o;
        return Double.compare(that.correctOption, correctOption) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), correctOption);
    }

    @Override
    public String toString() {
        return "EliminateOption{" +
                "correctOption=" + correctOption +
                '}';
    }
}
