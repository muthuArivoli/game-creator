package ooga.piece.movement;

public class bk implements Movement {
    private int units;
    public bk(int units){
        this.units = units;
    }

    @Override
    public String toString() {
        return "Backward{" +
                "units=" + units +
                '}';
    }
}
