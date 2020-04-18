package ooga.controller;

import java.io.FileNotFoundException;
import java.util.*;

import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.models.GridModel;
import ooga.parser.TemplateParser;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

public class GameController {
  private TemplateParser myTemplateParser;
  private GridModel myGridModel;
  private int activePlayer = 1;
  private String playerStage = "readyToView";
  private Piece selectedPiece;

  private Collection<Coordinate> validMoves = new ArrayList<>();

  public GameController () {
    this.myGridModel = new GridModel();
    this.myTemplateParser = new TemplateParser(myGridModel);
  }

  public void parseFile (String fileName) {
    try {
      myTemplateParser.parseTemplate(fileName);
    } catch (FileNotFoundException | InvalidGridException | InvalidPieceException e) {
      System.out.println(e.getMessage());
    }
  }

  public void handleClick(int x, int y) {
    if (activePlayer == 1) {
      switch(playerStage) {
        case "readyToView": // get valid moves
          validMoves = myGridModel.getValidMoves((new Coordinate(x, y)),1);
          playerStage = "readyToMove";

          for (Coordinate move: validMoves) {
            System.out.println(move.getRow() + "" + move.getCol());
          }
          break;
        case "readyToMove":
          if(myGridModel.getPiece(new Coordinate(x, y)).getSide() == activePlayer){
            validMoves.clear();
          } else {
            moveSelectedPiece(x, y);
            activePlayer = activePlayer == 1 ? 2 : 1;
          }
          playerStage = "readyToView";
          break;
      }
    }

  }


  public void moveSelectedPiece (int x, int y) {
    if(myGridModel.checkPieceExists(new Coordinate(x,y)) && selectedPiece.isCanCaptureJump()) {
      // don't switch sides
    } else {
      myGridModel.movePiece(selectedPiece, x, y);
      switchPlayers();
    }
  }

  private void switchPlayers () {
    selectedPiece = null;
    activePlayer = 1;
    activePlayer = activePlayer == 1 ? 2 : 1;
  }

  public GridModel getGridModel() {
    return myGridModel;
  }

  public String getGameName() {return myTemplateParser.getGameName();}
}
