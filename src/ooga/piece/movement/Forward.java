package ooga.piece.movement;

import ooga.piece.Coordinate;

public class Forward implements Movement{
    private int units;
    public Forward(int units){
        this.units = units;
    }

    @Override
    public boolean isValidMove(Coordinate initialPos, Coordinate finalPos) {
        return ((initialPos.getXpos()==finalPos.getXpos()) && ((units==-1 && initialPos.getYpos()<=finalPos.getYpos()) || (units!=-1 && initialPos.getYpos()+units>=finalPos.getYpos())));
    }
}
