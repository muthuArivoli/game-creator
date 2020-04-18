package ooga.piece.movement;

public class diagBkRight implements Movement{
  private int units;
  public diagBkRight(int units){
    this.units = units;
  }

  @Override
  public String toString() {
    return "Forward{" +
        "units=" + units +
        '}';
  }
}
