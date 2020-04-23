package goals;

import ooga.piece.Coordinate;

import java.util.List;
import java.util.function.Predicate;


public interface Goal {

    List<List<Coordinate>> getValidPaths(Coordinate position, int playerSide, Predicate<Coordinate> checkCoordinatesInBound);
}
