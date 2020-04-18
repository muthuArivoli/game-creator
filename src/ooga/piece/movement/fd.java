package ooga.piece.movement;

import ooga.models.GridModel;
import ooga.piece.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class fd implements Movement{
    private int units;
    public fd(int units){
        this.units = units;
    }

    @Override
    public List<Coordinate> getValidIndices(Coordinate position, int playerSide,
        GridModel gridModel) {
        List<Coordinate> moves = new ArrayList<>();

        int i = 0;
        do {
            i+=1;
            Coordinate c = new Coordinate(position.getRow()+i*playerSide,position.getCol());
            if (gridModel.checkPieceExists(c)) break;
            moves.add(c);
        } while (units == -1);

        return moves;
    }

    @Override
    public String toString() {
        return "Forward{" +
                "units=" + units +
                '}';
    }
}
