package ooga.piece.movement;

import ooga.piece.Coordinate;

import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a possible movement that a piece can make
 * @author Muthu Arivoli
 */
public interface Movement {
    /**
     * Returns all of the possible paths that a piece can take from a certain position
     * @param position the starting position of the piece
     * @param playerSide the side of the piece
     * @param checkCoordinatesInBound function that checks whether a coordinate is in the bounds of the game
     * @return all valid paths
     */
    List<List<Coordinate>> getValidPaths(Coordinate position, int playerSide, Predicate<Coordinate> checkCoordinatesInBound);
}
