package ooga.piece.movement;

public class fd implements Movement{
    private int units;
    public fd(int units){
        this.units = units;
    }

    @Override
    public String toString() {
        return "Forward{" +
                "units=" + units +
                '}';
    }
}
