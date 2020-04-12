package ooga.piece.movement;

import ooga.piece.Coordinate;

public class diagFdLeft implements Movement {
  private int units;
  public diagFdLeft(int units){
    this.units = units;
  }

  @Override
  public boolean isValidMove(Coordinate initialPos, Coordinate finalPos) {
    return ((initialPos.getXpos()==finalPos.getXpos()) && (initialPos.getYpos()<=finalPos.getYpos()) && (units==-1 || initialPos.getYpos()+units==finalPos.getYpos()));
  }

  @Override
  public String toString() {
    return "Forward{" +
        "units=" + units +
        '}';
  }
}