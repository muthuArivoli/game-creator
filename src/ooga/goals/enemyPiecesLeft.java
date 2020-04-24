package ooga.goals;

import ooga.models.GridModel;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

public class enemyPiecesLeft extends NumericGoal implements Goal {

  public enemyPiecesLeft(int myTarget) {
    super(myTarget);
  }

  @Override
  public int getWinner(GridModel gridModel, Piece lastPiece) {
    Coordinate bounds = gridModel.getDimensions();
    int player1Count = 0;
    int player2Count = 0;

    for (int i = 0; i < bounds.getRow(); i++) {
      for (int j = 0; j < bounds.getCol(); j++) {
        Coordinate c = new Coordinate(i, j);
        if (gridModel.checkPieceExists(c)) {
          if (gridModel.getPiece(c).getSide() == 1) {
            player1Count += 1;
          } else {
            player2Count += 2;
          }
        }
      }
    }

    if (player2Count <= myTarget) return 1;
    if (player1Count <= myTarget) return 2;

    return -1;
  }
}
