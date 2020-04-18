package ooga.piece.movement;

import ooga.piece.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class fd implements Movement{
    private int units;
    public fd(int units){
        this.units = units;
    }

    @Override
    public List<Coordinate> getValidIndices(Coordinate position, int playerSide) {
        List<Coordinate> moves = new ArrayList<>();
        if(units == -1) {
            for(int i=1;i<=units;i++){
                moves.add(new Coordinate(position.getXpos(),position.getYpos()+i*playerSide));
            }
        } else {
            moves.add(new Coordinate(position.getXpos(), position.getYpos()+units*playerSide));
        }

        return moves;
    }

    @Override
    public String toString() {
        return "Forward{" +
                "units=" + units +
                '}';
    }
}
