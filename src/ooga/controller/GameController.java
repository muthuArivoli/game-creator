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
  private boolean changed = false;

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

  public void handleClick(int row, int col) {
    System.out.println("Selected " + row+":"+col);

    Coordinate c = new Coordinate(row, col);
    if (activePlayer == 1) {
      switch(playerStage) {
        case READY_TO_VIEW: // get valid moves
//          if(myGridModel.checkPieceExists(c)){
//            System.out.println( myGridModel.getPiece(c).getSide() == activePlayer);
//          }
//          System.out.println(myGridModel);

          if(myGridModel.checkPieceExists(c) && myGridModel.getPiece(c).getSide() == activePlayer){
            selectedPiece = myGridModel.getPiece(c);
            validMoves = myGridModel.getValidMoves(c,1);
            togglePlayerStage(READY_TO_MOVE);
            setChanged(true);
            System.out.println("Valid moves generated:");
            for (Coordinate move: validMoves) {
              System.out.println(move.getRow() + "" + move.getCol());
            }
          }
          break;
        case READY_TO_MOVE:
          if(myGridModel.checkPieceExists(c) && myGridModel.getPiece(c).getSide() == activePlayer){
            System.out.println("Deactivating selected piece.");
            resetPlayerStage();
          } else {
            System.out.println("Moved piece");
            moveSelectedPiece(c);
          }
          setChanged(true);

          break;
      }
    }
  System.out.println(playerStage + "\n");
  }

  private void togglePlayerStage (String playerStage) {
//    playerStage = playerStage.equals(READY_TO_MOVE) ? READY_TO_VIEW  : READY_TO_MOVE;
    this.playerStage = playerStage;
  }

  public void moveSelectedPiece (Coordinate c) {
    if (validMoves.contains(c)){
      if(myGridModel.checkPieceExists(c) && selectedPiece.isCanCaptureJump()) {
        // don't switch sides
      } else {
        myGridModel.movePiece(selectedPiece, c);
        switchPlayers();
      }
    }

  }

  private void resetPlayerStage () {
//    activePlayer = activePlayer == 1 ? 2 : 1;
    togglePlayerStage(READY_TO_VIEW);
    selectedPiece = null;
    validMoves.clear();
  }

  private void switchPlayers () {
    resetPlayerStage();
//    activePlayer = activePlayer == 1 ? 2 : 1;
  }

  public GridModel getGridModel() {
    return myGridModel;
  }

  public void setChanged (boolean changed) {
    this.changed = changed;
  }

  public boolean isChanged() {
    return this.changed;
  }

  public String getGameName() {return myTemplateParser.getGameName();}
}
