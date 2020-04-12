package ooga.piece.movement;

import ooga.piece.Coordinate;

public class right implements Movement {
    private int units;
    public right(int units){
        this.units = units;
    }

    @Override
    public boolean isValidMove(Coordinate initialPos, Coordinate finalPos) {
        return ((initialPos.getYpos()==finalPos.getYpos()) && (initialPos.getXpos()<=finalPos.getXpos()) && (units==-1 || initialPos.getXpos()+units==finalPos.getXpos()));
    }

    @Override
    public String toString() {
        return "Rightward{" +
                "units=" + units +
                '}';
    }
}
