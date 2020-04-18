package ooga.piece.movement;

import ooga.models.GridModel;
import ooga.piece.Coordinate;

import java.util.List;


public interface Movement {
    List<Coordinate> getValidIndices(Coordinate position, int playerSide,
        GridModel gridModel);

    @Override
    String toString();
}
