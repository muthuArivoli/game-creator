package ooga.AI;

import javafx.util.Pair;
import ooga.models.GridModel;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessAI {
    private GridModel myGridModel;
    private int activePlayer;

    public ChessAI(GridModel myGridModel, int activePlayer) {
        this.myGridModel = myGridModel;
        this.activePlayer = activePlayer;
    }

    public List<Coordinate> getBestMove(int depthOfAnalysis) {
        if(depthOfAnalysis <= 0) {
            depthOfAnalysis = 1;
        }
        Pair<Integer, List<Coordinate>> resultList = recursiveMoveFinder(depthOfAnalysis, true);
        List<Coordinate> returnMove = resultList.getValue();
        return returnMove;
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

    private Pair<Integer, List<Coordinate>> recursiveMoveFinder(int depth, boolean isMaximizing) {
        // base case
        if(depth == 0) {
            return new Pair<Integer, List<Coordinate>>(evaluatePosition(), null);
        }

        // recursive case
        int bestMoveValue, tempActivePlayer;
        Coordinate currentBestMove = null;
        Coordinate currentBestPiece = null;
        if(isMaximizing) {
            bestMoveValue = Integer.MIN_VALUE;
            tempActivePlayer = activePlayer;
        } else {
            bestMoveValue = Integer.MAX_VALUE;
            tempActivePlayer = activePlayer == 1 ? -1 : 1;
        }

        List<Coordinate> playerPositions = myGridModel.getPositions(tempActivePlayer);
        Collections.shuffle(playerPositions);
        if(!playerPositions.isEmpty()) {
            for(Coordinate currPiece: playerPositions) {
                List<Coordinate> currValidMoves = myGridModel.getValidMoves(currPiece, tempActivePlayer);
                Collections.shuffle(currValidMoves);
                if(!currValidMoves.isEmpty()) {
                    for(Coordinate currMove: currValidMoves) {
                        myGridModel.movePiece(myGridModel.getPiece(currPiece), currMove);
                        int newPositionScore = recursiveMoveFinder(depth-1, !isMaximizing).getKey();
                        if(isMaximizing) {
                            if(newPositionScore > bestMoveValue) {
                                currentBestMove = currMove;
                                currentBestPiece = currPiece;
                                bestMoveValue = newPositionScore;
                            }
                        } else {
                            if (newPositionScore < bestMoveValue) {
                                currentBestMove = currMove;
                                currentBestPiece = currPiece;
                                bestMoveValue = newPositionScore;
                            }
                        }
                        myGridModel.undoLastMove();
                    }
                }
            }
        }
        List<Coordinate> returnList = new ArrayList<>();
        returnList.add(currentBestPiece);
        returnList.add(currentBestMove);

        return new Pair<>(bestMoveValue, returnList);
    }
}
