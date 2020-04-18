package ooga.piece.movement;

import ooga.piece.Coordinate;

import java.util.List;


public interface Movement {
    List<Coordinate> getValidIndices(Coordinate position, int playerSide);

    @Override
    String toString();
}
