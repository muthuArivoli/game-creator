package ooga.AI;

import javafx.util.Pair;
import ooga.exceptions.InvalidPieceException;
import ooga.goals.Goal;
import ooga.models.GameModel;
import ooga.models.GridModel;
import ooga.parser.PieceParser;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

import java.util.Collections;
import java.util.List;

public class PlacePiecesInRowAI implements AI{
    private GridModel myGridModel;
    private int activePlayer;
    private Goal myGoal;
    private GameModel myGameModel;

    public PlacePiecesInRowAI(GridModel myGridModel, int activePlayer) {
        this.myGridModel = myGridModel;
        this.activePlayer = activePlayer;
    }

    @Override
    public void setMyGameModel(GameModel myGameModel) {
        this.myGameModel = myGameModel;
    }

    @Override
    public void setMyGoal(Goal myGoal) {
        this.myGoal = myGoal;
    }

    @Override
    public List<Coordinate> getBestMove(int depthOfAnalysis) {
        if(depthOfAnalysis <= 0) {
            depthOfAnalysis = 1;
        }
        List<Coordinate> allPossibilities = myGridModel.emptySpaces();
        Collections.shuffle(allPossibilities);
        placeNewPiece(allPossibilities.get(0));

        return allPossibilities;
    }

    private Pair<Integer, Coordinate> recursiveMoveFinder(int depth, boolean isMaximizing) {
        if(depth == 0) {
            return new Pair<>(evaluatePosition(), null);
        }
        return null;
    }

    private int evaluatePosition() {
        return 0;
    }

    private void placeNewPiece(Coordinate coord) {
        try {
            Piece selectedPiece = myGameModel.generateNewPiece(activePlayer, coord);
            myGridModel.addPiece(selectedPiece, coord.getRow(), coord.getCol());

            List<Coordinate> validMoves = myGridModel.getValidMoves(coord, 1);

            if (validMoves.isEmpty()) {
                myGridModel.movePiece(selectedPiece, coord);
            } else {
                myGridModel.movePiece(selectedPiece, Collections.max(validMoves));
            }
        } catch (InvalidPieceException e) {
            //e.printStackTrace();
        }
    }
}
