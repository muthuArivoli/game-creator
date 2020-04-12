package ooga.piece;

import java.util.List;
import ooga.piece.movement.Movement;

public class Piece {
    private List<Movement> normalMovements;
    private List<Movement> captureMovements;
    private boolean canCapture;
    private boolean canPlace;
    private boolean canJump;

    public Piece(List<Movement> movements, List<Movement> captureMovements, boolean canCapture, boolean canPlace, boolean canJump) {
        this.normalMovements = movements;
        this.captureMovements = captureMovements;
        this.canCapture = canCapture;
        this.canPlace = canPlace;
        this.canJump = canJump;
    }
}