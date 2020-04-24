package ooga.AI;

import ooga.models.GridModel;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessAI {
    private GridModel myGridModel;
    private int activePlayer;

    public ChessAI(GridModel myGridModel, int activePlayer) {
        this.myGridModel = myGridModel;
        this.activePlayer = activePlayer;
    }

    public List<Coordinate> getBestMove() {
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
}
