package ooga.piece;

import java.util.List;
import java.util.Map;
import ooga.piece.movement.Movement;

public class Piece {
    private String name;
    private int side;
    private List<List<Movement>> normalFirstMovements;
    private List<List<Movement>> normalAnyMovements;

    private List<List<Movement>> captureMovements;

    private boolean canCapture;
    private boolean canPlace;
    private boolean canJump;

    public Piece(String name, int side, List<List<Movement>> normalFirstMovements,List<List<Movement>> normalAnyMovements, List<List<Movement>> captureMovements, boolean canCapture, boolean canPlace, boolean canJump) {
        this.name = name;
        this.side = side;
        this.normalFirstMovements = normalFirstMovements;
        this.normalAnyMovements = normalAnyMovements;
        this.captureMovements = captureMovements;

        this.canCapture = canCapture;
        this.canPlace = canPlace;
        this.canJump = canJump;
    }
}