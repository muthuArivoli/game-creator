package ooga.models;

import java.util.ArrayList;
import java.util.List;
import ooga.piece.Piece;

public class GridModel {
  private List<Piece> myGrid;
  public GridModel () {
    this.myGrid = new ArrayList<Piece>();
  }

  public void addPiece(Piece piece) {
    myGrid.add(piece);
  }
}
