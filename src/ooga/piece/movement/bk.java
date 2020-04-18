package ooga.piece.movement;

import ooga.piece.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class bk implements Movement {
    private int units;
    public bk(int units){
        this.units = units;
    }

    @Override
    public List<Coordinate> validMoves(Coordinate position, int playerSide) {
        List<Coordinate> moves = new ArrayList<>();
        for(int i=1;i<=units;i++){
            moves.add(new Coordinate(position.getXpos(),position.getYpos()-i));
        }
        return moves;
    }

    @Override
    public String toString() {
        return "Backward{" +
                "units=" + units +
                '}';
    }
}
