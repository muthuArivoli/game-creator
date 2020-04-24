package ooga.goals;

import ooga.models.GridModel;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

public class piecesInRow extends NumericGoal implements Goal{
  public piecesInRow(int myTarget) {
    super(myTarget);
  }

  @Override
  public int getWinner(GridModel gridModel, Piece lastPiece) {
    int side = lastPiece.getSide();
    Coordinate pos = lastPiece.getPosition();
    int r = pos.getRow();
    int c = pos.getCol();


    boolean ret =  checkRow(gridModel, side, r, c) || checkCol(gridModel, side, r, c) || checkDiag1(gridModel, side, r, c) || checkDiag2(gridModel, side, r, c);
    if(ret){
     return side;
    }
    return -1;
  }

  private boolean checkDiag1(GridModel gridModel, int side, int r, int c) {
    int count = 1;

    int cm = c-1;
    int rm = r -1;
    count = diagHelper(gridModel, side, count, rm, cm, -1, -1);

    cm = c+1;
    rm = r+1;
    count = diagHelper(gridModel, side, count, rm, cm, 1, 1);

    return count>=this.myTarget;
  }

  private boolean checkDiag2(GridModel gridModel, int side, int r, int c) {
    int count = 1;

    int cm = c + 1;
    int rm = r - 1;
    count = diagHelper(gridModel, side, count, rm, cm, -1, 1);

    cm = c - 1;
    rm = r + 1;
    count = diagHelper(gridModel, side, count, rm, cm, 1, -1);

    return count>=this.myTarget;
  }

  private int diagHelper(GridModel gridModel, int side, int count, int rm, int cm, int i, int j) {
    while (gridModel.checkPieceExists(new Coordinate(rm, cm))) {
      if (gridModel.getPiece(new Coordinate(rm, cm)).getSide() == side) {
        count += 1;
      } else {
        break;
      }
      rm += i;
      cm += j;
    }
    return count;
  }

  private boolean checkRow(GridModel gridModel, int side, int r, int c) {
    int count = 1;

    int m = c-1;
    count = rowHelper(gridModel, side, r, count, m, -1);

    m = c+1;
    count = rowHelper(gridModel, side, r, count, m, 1);

    return count>=this.myTarget;
  }

  private int rowHelper(GridModel gridModel, int side, int r, int count, int m, int i) {
    while (gridModel.checkPieceExists(new Coordinate(r, m))) {
      if (gridModel.getPiece(new Coordinate(r, m)).getSide() == side) {
        count += 1;
      } else {
        break;
      }
      m += i;
    }
    return count;
  }

  private boolean checkCol(GridModel gridModel, int side, int r, int c) {
    int count = 1;

    int m = r-1;
    count = colHelper(gridModel, side, c, count, m, -1);

    m = r+1;
    count = colHelper(gridModel, side, c, count, m, 1);

    return count>=this.myTarget;
  }

  private int colHelper(GridModel gridModel, int side, int c, int count, int m, int i) {
    while (gridModel.checkPieceExists(new Coordinate(m,c))) {
      if (gridModel.getPiece(new Coordinate(m, c)).getSide() == side) {
        count += 1;
      } else {
        break;
      }
      m += i;
    }
    return count;
  }

}
