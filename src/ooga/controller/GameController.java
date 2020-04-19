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
  private final String READY_TO_VIEW = "readyToView";
  private final String READY_TO_MOVE = "readyToMove";
  private String playerStage = READY_TO_VIEW;
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
    System.out.println(x+":"+y);
    System.out.println(myGridModel.getGrid());
    if (activePlayer == 1) {
      switch(playerStage) {
        case READY_TO_VIEW: // get valid moves
          validMoves = myGridModel.getValidMoves((new Coordinate(x, y)),1);
          togglePlayerStage();

          System.out.println("Valid moves generated:");
          for (Coordinate move: validMoves) {
            System.out.println(move.getRow() + "" + move.getCol());
          }
          break;
        case READY_TO_MOVE:
          if(myGridModel.getPiece(new Coordinate(x, y)).getSide() == activePlayer){
            validMoves.clear();
            togglePlayerStage();
          } else {
            moveSelectedPiece(x, y);
          }

          System.out.println("Piece moved");
          break;
      }
    }

  }

  private void togglePlayerStage () {
    playerStage = playerStage.equals(READY_TO_MOVE) ? READY_TO_VIEW  : READY_TO_MOVE;
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
//    activePlayer = activePlayer == 1 ? 2 : 1;
    playerStage = READY_TO_MOVE;

    selectedPiece = null;
    validMoves.clear();
  }

  public GridModel getGridModel() {
    return myGridModel;
  }

  public String getGameName() {return myTemplateParser.getGameName();}
}
