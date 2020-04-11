package ooga.piece.movement;

import ooga.piece.Coordinate;


public interface Movement {
    boolean isValidMove(Coordinate initialPos, Coordinate finalPos);

    @Override
    String toString();
}
