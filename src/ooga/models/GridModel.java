package ooga.models;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

public class GridModel {
  private Piece [][] myGrid;
  private int rows;
  private int cols;

  public GridModel () {

  }

  public void initGrid (int rows, int cols) {
    myGrid = new Piece [rows] [cols];
    this.rows = rows;
    this.cols = cols;
  }

  public Coordinate getDimensions () {
    return new Coordinate (myGrid.length, myGrid[0].length);
  }

  public void addPiece(Piece piece, int x, int y) {
    myGrid[x][y] = piece;
  }

  public Piece getPiece(Coordinate coordinate){
    return myGrid[coordinate.getXpos()][coordinate.getYpos()];
  }

  public boolean checkCoordInBounds (Coordinate c) {
    return c.getXpos() >=0 && c.getXpos() < rows &&
        c.getYpos() >= 0 && c.getYpos() < cols;
  }
  public boolean checkPieceExists(Coordinate c) {
    return checkCoordInBounds(c) &&
        myGrid[c.getXpos()][c.getYpos()] != null;
  }

  public Piece[][] getGrid () {
    return myGrid;
  }
}
