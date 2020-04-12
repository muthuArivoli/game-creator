package ooga.piece.movement;

import ooga.piece.Coordinate;

import java.util.List;


public interface Movement {
    List<Coordinate> validMoves(Coordinate position);

    @Override
    String toString();
}
