package ooga.AI;

import ooga.piece.Coordinate;

import java.util.List;

public interface AI {
    public List<Coordinate> getBestMove(int depthOfAnalysis);
}
