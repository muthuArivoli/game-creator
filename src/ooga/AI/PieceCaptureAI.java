package ooga.AI;

import javafx.util.Pair;
import ooga.goals.Goal;
import ooga.models.GameModel;
import ooga.models.GridModel;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PieceCaptureAI implements AI{
    private GridModel myGridModel;
    private GameModel myGameModel;
    private int activePlayer;
    private int alphaDefault = Integer.MIN_VALUE;
    private int betaDefault = Integer.MAX_VALUE;
    private Goal myGoal;

    public PieceCaptureAI(GridModel myGridModel, int activePlayer) {
        this.myGridModel = myGridModel;
        this.activePlayer = activePlayer;
    }

    @Override
    public List<Coordinate> getBestMove(int depthOfAnalysis) {
        if(depthOfAnalysis <= 0) {
            depthOfAnalysis = 1;
        }
        Pair<Integer, List<Coordinate>> resultList = recursiveMoveFinder(depthOfAnalysis, true, alphaDefault, betaDefault);
        List<Coordinate> returnMove = resultList.getValue();
        //System.out.println("Best Move Value: " + resultList.getKey());
        return returnMove;
    }

    @Override
    public void setMyGoal(Goal myGoal) {
        this.myGoal = myGoal;
    }

    @Override
    public void setMyGameModel(GameModel myGameModel) {
        this.myGameModel = myGameModel;
    }

    private int evaluatePosition() {
        int positionScore = 0;
        int nonActivePlayer = activePlayer == 1 ? -1 : 1;
        List<Coordinate> playerPositions = myGridModel.getPositions(activePlayer);
        List<Coordinate> enemyPositions = myGridModel.getPositions(nonActivePlayer);

        for(Coordinate currCoord: playerPositions) {
            Piece currPiece = myGridModel.getPiece(currCoord);
            positionScore += currPiece.getPointValue();
        }
        for(Coordinate enemyCurrCoord: enemyPositions) {
            Piece enemyCurrPiece = myGridModel.getPiece(enemyCurrCoord);
            positionScore -= enemyCurrPiece.getPointValue();
        }
        return positionScore;
    }

    private Pair<Integer, List<Coordinate>> recursiveMoveFinder(int depth, boolean isMaximizing, int alphaValue, int betaValue) {
        // base case
        if(depth == 0) {
            return new Pair<>(evaluatePosition(), null);
        }

        // recursive case
        int bestMoveValue, tempActivePlayer;
        int tempAlpha = alphaValue;
        int tempBeta = betaValue;
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
                        int newPositionScore = recursiveMoveFinder(depth-1, !isMaximizing, tempAlpha, tempBeta).getKey();
                        if(isMaximizing) {
                            if(newPositionScore > bestMoveValue) {
                                currentBestMove = currMove;
                                currentBestPiece = currPiece;
                                bestMoveValue = newPositionScore;
                            }
                            tempAlpha = Math.max(tempAlpha, newPositionScore);
                        } else {
                            if (newPositionScore < bestMoveValue) {
                                currentBestMove = currMove;
                                currentBestPiece = currPiece;
                                bestMoveValue = newPositionScore;
                            }
                            tempBeta = Math.min(tempBeta, newPositionScore);
                        }
                        myGridModel.undoLastMove();
                        if(tempBeta <= tempAlpha) {
                            break;
                        }
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
