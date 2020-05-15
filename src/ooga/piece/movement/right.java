package ooga.piece.movement;

import ooga.piece.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a rightwards movement on the board
 * @author Muthu Arivoli
 */
public class right implements Movement {
    private int rangeBegin;
    private int rangeEnd;

    public right(int rangeBegin, int rangeEnd){
        this.rangeBegin = rangeBegin;
        this.rangeEnd = rangeEnd;
    }

    /**
     * Returns all of the possible paths that a piece can take in the rightwards direction
     * @param position the starting position of the piece
     * @param playerSide the side of the piece
     * @param checkCoordinatesInBound function that checks whether a coordinate is in the bounds of the game
     * @return valid paths for a rightward movement
     */
    @Override
    public List<List<Coordinate>> getValidPaths(Coordinate position, int playerSide, Predicate<Coordinate> checkCoordinatesInBound) {
        List<List<Coordinate>> paths = new ArrayList<>();
        Coordinate nextCoord = new Coordinate(position.getRow(),position.getCol()+rangeBegin*playerSide);
        if(!checkCoordinatesInBound.test(nextCoord)){
            return paths;
        }
        paths.add(new ArrayList<>());
        paths.get(0).add(nextCoord);
        for(int i=rangeBegin+1;i<=rangeEnd;i++){
            nextCoord = new Coordinate(position.getRow(),position.getCol()+i*playerSide);
            if(!checkCoordinatesInBound.test(nextCoord)){
                return paths;
            }
            paths.add(new ArrayList<>(paths.get(paths.size()-1)));
            paths.get(paths.size()-1).add(nextCoord);
        }
        return paths;
    }
}
