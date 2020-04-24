package ooga.goals;

import ooga.models.GridModel;
import ooga.piece.Piece;


public interface Goal {
    int checkGoal(GridModel gridModel, Piece lastPiece);
}
