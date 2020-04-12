package ooga.piece.movement;

import ooga.piece.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class diagBkLeft implements Movement {
  private int units;
  public diagBkLeft(int units){
    this.units = units;
  }

  @Override
  public List<Coordinate> validMoves(Coordinate position) {
    List<Coordinate> moves = new ArrayList<>();
    for(int i=1;i<=units;i++){
      moves.add(new Coordinate(position.getXpos()-i,position.getYpos()-i));
    }
    return moves;
  }

  @Override
  public String toString() {
    return "Forward{" +
        "units=" + units +
        '}';
  }
}
