package ooga.piece.movement;

import ooga.piece.Coordinate;

import java.util.List;
import java.util.function.Predicate;


public interface Movement {

    List<List<Coordinate>> getValidPaths(Coordinate position, int playerSide, Predicate<Coordinate> checkCoordinatesInBound);
}
