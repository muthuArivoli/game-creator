package ooga.piece.movement;

public class right implements Movement {
    private int units;
    public right(int units){
        this.units = units;
    }

    @Override
    public String toString() {
        return "Rightward{" +
                "units=" + units +
                '}';
    }
}
