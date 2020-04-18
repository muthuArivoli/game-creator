package ooga.piece.movement;

public class diagFdLeft implements Movement {
  private int units;
  public diagFdLeft(int units){
    this.units = units;
  }

  @Override
  public String toString() {
    return "Forward{" +
        "units=" + units +
        '}';
  }
}
