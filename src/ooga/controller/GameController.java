package ooga.controller;

import java.io.FileNotFoundException;
import java.util.*;

import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.models.GameModel;
import ooga.models.GridModel;
import ooga.parser.PieceParser;
import ooga.parser.TemplateParser;
import ooga.piece.Coordinate;
import ooga.piece.Piece;
import ooga.piece.movement.Movement;

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

  private List<Coordinate> validMoves = new ArrayList<>();

  public GameController () {
    this.myGridModel = new GridModel();
    this.myTemplateParser = new TemplateParser(myGridModel, myGameModel);
  }

  public void parseFile (String fileName) {
    try {
      myTemplateParser.parseTemplate(fileName);
    } catch (FileNotFoundException | InvalidGridException | InvalidPieceException e) {
      System.out.println(e.getMessage());
    }
  }

  public void handleClick(int row, int col) {
//    System.out.println(row+"."+col);

    Coordinate c = new Coordinate(row, col);
    if (myGameModel.isCanPlace()) {
      handlePlaceableClick(c);
    } else {
      handleNonPlaceableClick(c); // for chess style games
    };
  }

  private void handleNonPlaceableClick(Coordinate c) {
    if (activePlayer == 1) {
      switch(playerStage) {
        case READY_TO_VIEW: // get valid moves
//          if(myGridModel.checkPieceExists(c)){
//            System.out.println( myGridModel.getPiece(c).getSide() == activePlayer);
//          }
//          System.out.println(myGridModel);

          if(myGridModel.checkPieceExists(c) && myGridModel.getPiece(c).getSide() == activePlayer){
            selectedPiece = myGridModel.getPiece(c);
            System.out.println("Selected: " + selectedPiece.getPieceName());
            validMoves = myGridModel.getValidMoves(c,activePlayer);
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
    } else { // if it is the AI's turn
      switch(playerStage) {
        case READY_TO_VIEW:
          List<Coordinate> bestMove = findBestChessMove();
          Coordinate currPiece = bestMove.get(0);
          Coordinate currMove = bestMove.get(1);

          validMoves = myGridModel.getValidMoves(currPiece, activePlayer);
          if(!validMoves.isEmpty()) {
            selectedPiece = myGridModel.getPiece(currPiece);
            moveSelectedPiece(currMove);
          }
          setChanged(true);
          break;


          /* List<Coordinate> playerPositions = myGridModel.getPositions(activePlayer);

          while(!playerPositions.isEmpty()){
            int randomIndex = new Random().nextInt(playerPositions.size());
            Coordinate coord = playerPositions.get(randomIndex);
            playerPositions.remove(randomIndex);

            validMoves = myGridModel.getValidMoves(coord, activePlayer);
            if(!validMoves.isEmpty()) {
              selectedPiece = myGridModel.getPiece(coord);

//              System.out.println(selectedPiece.getPieceName() + " "+ selectedPiece.getSide()+ " @ "+ selectedPiece.getPosition());
//              System.out.println("Moving to: " + coord);

              moveSelectedPiece(validMoves.get(new Random().nextInt(validMoves.size())));
              break;
            }
          }
          setChanged(true);
          break; */
      }
    }
    System.out.println("Player " + activePlayer + " is " + playerStage + "\n");
  }

  private void handlePlaceableClick(Coordinate c) {
    if (activePlayer == 1) {
      try {
        System.out.println(c);
        selectedPiece = new PieceParser(myGameModel.getPieceJSON()).generatePiece("dime" + (activePlayer == -1 ? 2 : 1), c.getRow(), c.getCol());
        myGridModel.addPiece(selectedPiece, c.getRow(), c.getCol());

        List<Coordinate> validMoves = myGridModel.getValidMoves(c, 1);

        if(validMoves.isEmpty()) {
          myGridModel.movePiece(selectedPiece, c);
        } else {
          myGridModel.movePiece(selectedPiece,  Collections.max(validMoves));
        }

        myGridModel.print();
        setChanged(true);
      } catch (InvalidPieceException e) {
        e.printStackTrace();
      }
    } else {

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

  private int evaluatePosition() {
    int positionScore = 0;

    int nonActivePlayer = activePlayer == 1 ? -1 : 1;
    List<Coordinate> playerPositions = myGridModel.getPositions(activePlayer);
    List<Coordinate> enemyPositions = myGridModel.getPositions(nonActivePlayer);

    for(Coordinate currCoord: playerPositions) {
      Piece currPiece = myGridModel.getPiece(currCoord);
      positionScore += getChessPieceValue(currPiece.getPieceName());
    }
    for(Coordinate enemyCurrCoord: enemyPositions) {
      Piece enemyCurrPiece = myGridModel.getPiece(enemyCurrCoord);
      positionScore -= getChessPieceValue(enemyCurrPiece.getPieceName());
    }
    return positionScore;
  }

  //this is just for testing purposes and will be replaced with data driven design!
  private int getChessPieceValue(String pieceName) {
    if(pieceName.equals("pawn")) {
      return 100;
    } else if(pieceName.equals("knight")) {
      return 350;
    } else if(pieceName.equals("rook")) {
      return 525;
    } else if(pieceName.equals("bishop")) {
      return 350;
    } else if(pieceName.equals("queen")) {
      return 1000;
    } else if(pieceName.equals("king")) {
      return 10000;
    } else {
      return 0;
    }
  }

  private List<Coordinate> findBestChessMove() {
    Coordinate currentBestMove = null;
    Coordinate currentBestPiece = null;
    int currPositionScore = evaluatePosition();
    int bestMoveValue = Integer.MIN_VALUE;
    int nonActivePlayer = activePlayer == 1 ? -1 : 1;

    List<Coordinate> playerPositions = myGridModel.getPositions(activePlayer);
    Collections.shuffle(playerPositions);
    if(!playerPositions.isEmpty()) {
      for(Coordinate currPiece: playerPositions) {
        System.out.println(currPiece);
        List<Coordinate> currValidMoves = myGridModel.getValidMoves(currPiece, activePlayer);
        Collections.shuffle(currValidMoves);
        if(!currValidMoves.isEmpty()) {
          for(Coordinate currMove: currValidMoves) {
            int currMoveValueChange = 0;
            if(myGridModel.getPiece(currMove) != null && myGridModel.getPiece(currMove).getSide() == nonActivePlayer) {
              currMoveValueChange = getChessPieceValue(myGridModel.getPiece(currMove).getPieceName());
            }
            int newPositionScore = currPositionScore + currMoveValueChange;
            if(newPositionScore > bestMoveValue) {
              currentBestMove = currMove;
              currentBestPiece = currPiece;
              bestMoveValue = newPositionScore;
            }
          }
        }
      }
    }

    List<Coordinate> returnList = new ArrayList<>();
    returnList.add(currentBestPiece);
    returnList.add(currentBestMove);
    System.out.println("Best Piece:" + currentBestPiece);
    System.out.println("Best Move:" + currentBestMove);
    System.out.println("Best Score:" + bestMoveValue);
    return returnList;
  }

}
