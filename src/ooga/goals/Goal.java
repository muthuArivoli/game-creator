package ooga.goals;

import ooga.models.GridModel;
import ooga.piece.Piece;


public interface Goal {
    int getWinner(GridModel gridModel, Piece lastPiece);
}
