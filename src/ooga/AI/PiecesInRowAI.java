package ooga.AI;

import ooga.models.GridModel;
import ooga.piece.Coordinate;

import java.util.List;

public class PiecesInRowAI implements AI{
    private GridModel myGridModel;
    private int activePlayer;

    public PiecesInRowAI(GridModel myGridModel, int activePlayer) {
        this.myGridModel = myGridModel;
        this.activePlayer = activePlayer;
    }

    @Override
    public List<Coordinate> getBestMove(int depthOfAnalysis) {
        System.out.println("sdojsdosds");
        return null;
    }
}
