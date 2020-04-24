package ooga.models;

import java.util.ArrayList;
import java.util.LinkedList;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class GridModel {
  private LinkedList<MoveRecord> previousMove = new LinkedList<MoveRecord>();
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
      previousMove.push(new MoveRecord(piece.getPosition(), c));
      removePiece(piece.getPosition());
      piece.setPosition(c.getRow(), c.getCol());
      addPiece(piece, c.getRow(), c.getCol());
      piece.incrementMove();
  }

  public void removePiece(Coordinate c) {
   // System.out.println("removing " + c.getRow() + " " + c.getCol());
    myGrid[c.getRow()][c.getCol()] = null;
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

    Piece piece = getPiece(c);
    List<List<Coordinate>> possiblePaths;
    if(piece.getMoveNumber() == 1){
      possiblePaths = piece.getValidPaths(c, playerSide, checkCoordinateInBounds,piece.getNormalFirstMovements());
    }
    else {
      possiblePaths = piece.getValidPaths(c, playerSide, checkCoordinateInBounds,piece.getNormalAnyMovements());
    }
    if(!piece.isCanJump()){
      removeJumps(possiblePaths);
    }
    removePieceOverlap(possiblePaths,piece.getSide()); //remove paths that results in the final position overlapping with another piece of same side
    removePieceOverlap(possiblePaths,piece.getOppositeSide());

    List<List<Coordinate>> possibleCapturePaths = piece.getValidPaths(c,playerSide,checkCoordinateInBounds,piece.getCaptureMovements());
    if(!piece.isCanJump()){
      removeJumps(possibleCapturePaths);
    }
    removePieceOverlap(possibleCapturePaths,piece.getSide());
    keepPieceOverlap(possibleCapturePaths);

    possiblePaths.addAll(possibleCapturePaths);
    Set<Coordinate> validMoves = new HashSet<>();
    for(int i=0;i<possiblePaths.size();i++){
      validMoves.add(possiblePaths.get(i).get(possiblePaths.get(i).size()-1));
    }
    //System.out.println(validMoves);
    return new ArrayList<Coordinate>(validMoves);
  }

  public List<Coordinate> getPositions (int playerSide) {
    List<Coordinate> positions = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        Coordinate c = new Coordinate(i, j);
        if (checkPieceExists(c) && getPiece(c).getSide() == playerSide) {
          // System.out.println(playerSide);
          positions.add(getPiece(c).getPosition());
        }
      }
    }
    // System.out.println(positions);
    return positions;
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

  private void keepPieceOverlap(List<List<Coordinate>> possiblePaths){
    for(int i=0;i<possiblePaths.size();i++){
      if(!checkPieceExists(possiblePaths.get(i).get(possiblePaths.get(i).size()-1))){
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

  public void print () {
    for (int i = 0; i < myGrid.length; i++) {
      for (int j = 0; j < myGrid[0].length; j++) {
        System.out.print(myGrid[i][j]);
      }
      System.out.println();
    }
  }
  public Piece[][] getGrid () {
    return myGrid;
  }

  public void undoLastMove() {
    previousMove.pop().undoThisMove();
  }

  class MoveRecord {
    Piece movedPiece;
    Piece removedPiece;
    Coordinate originalLocation;
    Coordinate newLocation;

    MoveRecord(Coordinate originalLocation, Coordinate newLocation) {
      this.originalLocation = originalLocation;
      this.newLocation = newLocation;
      this.movedPiece = getPiece(originalLocation);
      this.removedPiece = getPiece(newLocation);
    }

    public void undoThisMove() {
      removePiece(originalLocation);
      removePiece(newLocation);

      if(movedPiece != null) {
        movedPiece.setPosition(originalLocation.getRow(), originalLocation.getCol());
        addPiece(movedPiece, originalLocation.getRow(), originalLocation.getCol());
      }
      if(removedPiece != null) {
        removedPiece.setPosition(newLocation.getRow(), newLocation.getCol());
        addPiece(removedPiece, newLocation.getRow(), newLocation.getCol());
      }
    }
  }
}
