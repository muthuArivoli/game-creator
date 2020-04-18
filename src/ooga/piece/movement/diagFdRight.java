package ooga.piece.movement;

import ooga.piece.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class diagFdRight implements Movement{
  private int rangeBegin;
  private int rangeEnd;

  public diagFdRight(int rangeBegin, int rangeEnd){
    this.rangeBegin = rangeBegin;
    this.rangeEnd = rangeEnd;
  }

  @Override
  public List<List<Coordinate>> getValidPaths(Coordinate position, int playerSide, Predicate<Coordinate> checkCoordinatesInBound) {
    List<List<Coordinate>> paths = new ArrayList<>();
    Coordinate nextCoord = new Coordinate(position.getRow()-rangeBegin*playerSide,position.getCol()+rangeBegin*playerSide);
    if(!checkCoordinatesInBound.test(nextCoord)){
      return paths;
    }
    paths.add(new ArrayList<>());
    paths.get(0).add(nextCoord);
    for(int i=rangeBegin+1;i<=rangeEnd;i++){
      nextCoord = new Coordinate(position.getRow()-i*playerSide,position.getCol()+i*playerSide);
      if(!checkCoordinatesInBound.test(nextCoord)){
        return paths;
      }
      paths.add(new ArrayList<>(paths.get(paths.size()-1)));
      paths.get(paths.size()-1).add(nextCoord);
    }
    return paths;
  }
}
