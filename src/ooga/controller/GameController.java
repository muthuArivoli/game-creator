package ooga.controller;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import ooga.AI.AI;
import ooga.AI.PieceCaptureAI;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.goals.Goal;
import ooga.models.GameModel;
import ooga.models.GridModel;
import ooga.parser.PieceParser;
import ooga.parser.TemplateParser;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

public class GameController {
  private TemplateParser myTemplateParser;
  private GridModel myGridModel;
  private GameModel myGameModel = new GameModel();

  private int activePlayer = 1;
  private final String READY_TO_VIEW = "readyToView";
  private final String READY_TO_MOVE = "readyToMove";
  private String playerStage = READY_TO_VIEW;
  private Piece selectedPiece;
  private boolean changed = false;
  private boolean aiEnabled = true;
  private boolean gameOver = false;

  private List<Coordinate> validMoves = new ArrayList<>();

  public GameController () {
    this.myGridModel = new GridModel();
    this.myTemplateParser = new TemplateParser(myGridModel, myGameModel);
  }

  public void parseFile (String fileName) {
    try {
      myTemplateParser.parseTemplate(fileName);
    } catch (FileNotFoundException | InvalidGridException | InvalidPieceException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
      //System.out.println(e.getMessage());
    }
  }

  public void handleClick(int row, int col) {
    if(!gameOver) {
      Coordinate c = new Coordinate(row, col);
      if (myGameModel.isCanPlace()) {
        handlePlaceableClick(c);
      } else {
        handleNonPlaceableClick(c); // for chess style games
      }
    }
  }

  public int checkGameEnd () {
    for (Goal goal: myGameModel.getGoals()) {
      int winner = goal.getWinner(myGridModel, selectedPiece);

      if(winner != 0) {
        gameOver = true;
        return winner;
      }
    }
    //System.out.println("goal not achieved");
    return 0;
  }

  private void handleNonPlaceableClick(Coordinate c) {
    if (activePlayer == 1 || !aiEnabled) {
      switch(playerStage) {
        case READY_TO_VIEW: // get valid moves
//          if(myGridModel.checkPieceExists(c)){
//            System.out.println( myGridModel.getPiece(c).getSide() == activePlayer);
//          }
//          System.out.println(myGridModel);

          if(myGridModel.checkPieceExists(c) && myGridModel.getPiece(c).getSide() == activePlayer){
            selectedPiece = myGridModel.getPiece(c);
            //System.out.println("Selected: " + selectedPiece.getPieceName());
            validMoves = myGridModel.getValidMoves(c,activePlayer);
            togglePlayerStage(READY_TO_MOVE);
            setChanged(true);
            //System.out.println("Valid moves generated:");
            for (Coordinate move: validMoves) {
              //System.out.println(move.getRow() + "" + move.getCol());
            }
          }
          break;
        case READY_TO_MOVE:
          if(myGridModel.checkPieceExists(c) && myGridModel.getPiece(c).getSide() == activePlayer){
            //System.out.println("Deactivating selected piece.");
            resetPlayerStage();
          } else {
            //.out.println("Moved piece");
            moveSelectedPiece(c);
          }
          setChanged(true);

          break;
      }
    } else { // if it is the AI's turn
      switch(playerStage) {
        case READY_TO_VIEW:
          try {
            AI newAI = myGameModel.getNewGameAI(myGridModel, activePlayer);
            List<Coordinate> bestMove = newAI.getBestMove(4); //change to be user inputted difficulty level
            Coordinate currPiece = bestMove.get(0);
            Coordinate currMove = bestMove.get(1);

            validMoves = myGridModel.getValidMoves(currPiece, activePlayer);
            if (!validMoves.isEmpty()) {
              selectedPiece = myGridModel.getPiece(currPiece);
              moveSelectedPiece(currMove);
            }
            setChanged(true);
            break;
          } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | ClassNotFoundException e){
            //System.out.println(e.getMessage());
        }
      }
    }
    //System.out.println("Player " + activePlayer + " is " + playerStage + "\n");
  }

  private void handlePlaceableClick(Coordinate c) {
    if (activePlayer == 1 || !aiEnabled) {
      try {
        if(!myGridModel.checkPieceExists(c)) {
          selectedPiece = new PieceParser(myGameModel.getPieceJSON()).generatePiece("dime" + (activePlayer == -1 ? 2 : 1), c.getRow(), c.getCol());
          myGridModel.addPiece(selectedPiece, c.getRow(), c.getCol());

          List<Coordinate> validMoves = myGridModel.getValidMoves(c, 1);

          if(validMoves.isEmpty()) {
            myGridModel.movePiece(selectedPiece, c);
          } else {
            myGridModel.movePiece(selectedPiece,  Collections.max(validMoves));
          }

//        myGridModel.print();
          setChanged(true);
          activePlayer = activePlayer == 1 ? -1 : 1;
        }
      } catch (InvalidPieceException e) {
        //e.printStackTrace();
      }
    } else {
      try {
        if(aiEnabled) {
          // need to include selected piece
          AI newAI = myGameModel.getNewGameAI(myGridModel, activePlayer);
          newAI.setMyGoal(myGameModel.getGoals().get(0));
          newAI.setMyGameModel(myGameModel);
          List<Coordinate> newMoves = newAI.getBestMove(8);
          selectedPiece = myGridModel.getPiece(newMoves.get(0));

          setChanged(true);
          activePlayer = activePlayer == 1 ? -1 : 1;
        }
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | ClassNotFoundException e) {
        //System.out.println(e.getMessage());
      }
    }
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
    togglePlayerStage(READY_TO_VIEW);
    selectedPiece = null;
    validMoves.clear();
  }

  private void switchPlayers () {
    resetPlayerStage();

    activePlayer = activePlayer == 1 ? -1 : 1;
  }

  public GridModel getGridModel() {
    return myGridModel;
  }
  public GameModel getGameModel () {return myGameModel;}
  public void setChanged (boolean changed) {
    this.changed = changed;
  }

  public boolean isChanged() {
    return this.changed;
  }

  public List<Coordinate> getValidMoves() {
    return validMoves;
  }
  public String getGameName() {return myTemplateParser.getGameName();}

  public Coordinate getSelectedPiecePosition(){
    if (selectedPiece != null) {
      return selectedPiece.getPosition();
    }
    return null;
  }

  public void toggleAI () {
    aiEnabled = !aiEnabled;
  }

  public boolean isAiEnabled () {
    return aiEnabled;
  }
}
