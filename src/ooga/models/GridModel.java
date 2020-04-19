package ooga.models;

import ooga.piece.Coordinate;
import ooga.piece.Piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

  public void movePiece(Piece piece, Coordinate c) {
    Coordinate prevCoordinate = piece.getPosition();
    removePiece(prevCoordinate.getRow(), prevCoordinate.getCol());
    piece.setPosition(c.getRow(), c.getCol());
    addPiece(piece, c.getRow(), c.getCol());
  }

  public void removePiece(int x, int y) {
    myGrid[x][y] = null;
  }

  public Piece getPiece(Coordinate coordinate){
//    System.out.println("Getting coordinate: " + coordinate.getRow() + " " + coordinate.getCol());
//    for (int i = 0; i < myGrid.length; i++) {
//      for (int j = 0; j < myGrid[0].length; j++) {
//        System.out.print(myGrid[i][j]);
//      }
//      System.out.println();
//    }
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

  public Set<Coordinate> getValidMoves(Coordinate c, int playerSide){
    if(!checkPieceExists(c)){
      //HANDLE EXCEPTION
    }
    Predicate<Coordinate> checkCoordinateInBounds = coord ->(coord.getRow() >=0 && coord.getRow() < rows &&
            coord.getCol() >= 0 && coord.getCol() < cols);

    Piece piece = getPiece(c);

    List<List<Coordinate>> possiblePaths =  piece.getValidPaths(c, playerSide, checkCoordinateInBounds);
    if(!piece.isCanJump()){
      removeJumps(possiblePaths);
    }
    removePieceOverlap(possiblePaths,getPiece(c).getSide()); //remove paths that results in the final position overlapping with another piece of same side

    Set<Coordinate> validMoves = new HashSet<>();
    for(int i=0;i<possiblePaths.size();i++){
      validMoves.add(possiblePaths.get(i).get(possiblePaths.get(i).size()-1));
    }
    return validMoves;
  }

  private void removePieceOverlap(List<List<Coordinate>> possiblePaths, int pieceSide) {
    for(int i=0;i<possiblePaths.size();i++){
      if(checkPieceExists(possiblePaths.get(i).get(possiblePaths.get(i).size()-1))
              && getPiece(possiblePaths.get(i).get(possiblePaths.get(i).size()-1)).getSide()==pieceSide){
        possiblePaths.remove(i);
        i--;
      }
    }
  }

  private void removeJumps(List<List<Coordinate>> possiblePaths) {
    for(int i=0;i<possiblePaths.size();i++){
      for(int k=0;k<possiblePaths.get(i).size()-1;k++){
        if(checkPieceExists(possiblePaths.get(i).get(k))){
          possiblePaths.remove(i);
          i--;
          break;
        }
      }
    }
  }

  public Piece[][] getGrid () {
    return myGrid;
  }
}
