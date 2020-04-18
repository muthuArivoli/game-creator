package ooga.models;

import ooga.piece.Coordinate;
import ooga.piece.Piece;

import java.util.List;
import java.util.function.Predicate;

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
    return new Coordinate (rows, cols);
  }

  public void addPiece(Piece piece, int x, int y) {
    myGrid[x][y] = piece;
  }

  public Piece getPiece(Coordinate coordinate){
    return myGrid[coordinate.getRow()][coordinate.getCol()];
  }

  public boolean checkCoordInBounds (Coordinate c) {
    return c.getRow() >=0 && c.getRow() < rows &&
        c.getCol() >= 0 && c.getCol() < cols;
  }
  public boolean checkPieceExists(Coordinate c) {
    return checkCoordInBounds(c) &&
        myGrid[c.getRow()][c.getCol()] != null;
  }

  public List<Coordinate> getValidMoves(Coordinate c, int playerSide){
    if(!checkPieceExists(c)){
      //HANDLE EXCEPTION
    }
    Predicate<Coordinate> checkCoordinateInBounds = coord ->(coord.getRow() >=0 && coord.getRow() < rows &&
            coord.getCol() >= 0 && coord.getCol() < cols);
    List<List<Coordinate>> possiblePaths =  getPiece(c).getValidPaths(c, playerSide, checkCoordinateInBounds);
    return null;
    //HANDLE JUMP
  }

  public Piece[][] getGrid () {
    return myGrid;
  }
}
