package ooga.models;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

public class GridModel {
  private Piece [][] myGrid;
  public GridModel () {

  }

  public void initGrid (int rows, int cols) {
    myGrid = new Piece [rows] [cols];
  }

  public Pair<Integer, Integer> getDimensions () {
    return new Pair <Integer, Integer> (myGrid.length, myGrid[0].length);
  }

  public void addPiece(Piece piece, int x, int y) {
    myGrid[x][y] = piece;
  }

  public Piece getPiece(Coordinate coordinate){
    return myGrid[coordinate.getXpos()][coordinate.getYpos()];
  }

  public Piece[][] getGrid () {
    return myGrid;
  }
}
