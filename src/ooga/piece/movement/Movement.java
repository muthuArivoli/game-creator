package ooga.piece.movement;

import ooga.models.GridModel;
import ooga.piece.Coordinate;

import java.util.List;
import java.util.function.Predicate;


public interface Movement {
    List<Coordinate> getValidIndices(Coordinate position, int playerSide,
        GridModel gridModel);

    List<List<Coordinate>> getValidPaths(Coordinate position, int playerSide, Predicate<Coordinate> checkCoordinatesInBound);

    @Override
    String toString();
}
