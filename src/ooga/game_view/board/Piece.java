package ooga.game_view.board;

public class Piece {
  private double xRadius;
  private double yRadius;

  public Piece(double tileX, double tileY){
    this.xRadius = tileX/2;
    this.yRadius = tileY/2;
  }
  
}
