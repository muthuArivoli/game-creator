package ooga.game_view.board;

public class PieceView {
  private double xRadius;
  private double yRadius;

  public PieceView(double tileX, double tileY){
    this.xRadius = tileX/2;
    this.yRadius = tileY/2;
  }

}
