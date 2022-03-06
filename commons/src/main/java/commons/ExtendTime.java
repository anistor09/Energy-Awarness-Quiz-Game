package commons;

import java.util.Objects;

public class ExtendTime extends JokerCard{
    private int additionalTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExtendTime that = (ExtendTime) o;
        return additionalTime == that.additionalTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), additionalTime);
    }

    public ExtendTime(String name, String description, boolean onlyMultiplayer, int additionalTime) {
        super(name, description, onlyMultiplayer);
        this.additionalTime = additionalTime;
    }

    @Override
    public void useCard(Player player) {

    }


    public int getAdditionalTime() {
        return additionalTime;
    }

    public void setAdditionalTime(int additionalTime) {
        this.additionalTime = additionalTime;
    }

    @Override
    public String toString() {
        return "ExtendTime{" +
                "additionalTime=" + additionalTime +
                '}';
    }
}
