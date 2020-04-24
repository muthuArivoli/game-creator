package ooga.goals;

import ooga.models.GridModel;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

public class piecesInRow extends NumericGoal implements Goal{
  private int colBounds;
  private int rowBounds;

  public piecesInRow(int myTarget) {
    super(myTarget);
  }

  @Override
  public int getWinner(GridModel gridModel, Piece lastPiece) {
    this.colBounds = gridModel.getDimensions().getCol();
    this.rowBounds = gridModel.getDimensions().getRow();

    int side = lastPiece.getSide();
    Coordinate pos = lastPiece.getPosition();
    int r = pos.getRow();
    int c = pos.getCol();


    boolean ret =  checkRow(gridModel, side, r, c);
    return -1;
  }

  private boolean checkRow(GridModel gridModel, int side, int r, int c) {
    int count = 0;

    int m = c-1;
    while ( colInBounds(m) ) {
      if (gridModel.getPiece(new Coordinate(r, m)).getSide()==side) {
        count+=1;
      } else {
        break;
      }
      m += -1;
    }

    m = c+1;
    while ( colInBounds(m) ) {
      if (gridModel.getPiece(new Coordinate(r, m)).getSide()==side) {
        count+=1;
      } else {
        break;
      }
      m += 1;
    }

    return count>=this.myTarget;
  }

  private boolean colInBounds (int c) {
    return c > -1 && c < colBounds;
  }
  private boolean rowInBounds (int r) {
    return r > -1 && r < rowBounds;
  }
}
