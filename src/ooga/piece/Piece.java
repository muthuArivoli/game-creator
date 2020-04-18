package ooga.piece;

import java.util.List;
import java.util.Map;
import ooga.piece.movement.Movement;

public class Piece {
    private String name;
    private int side;
    private Coordinate position;

    private List<Movement> normalFirstMovements;
    private List<Movement> normalAnyMovements;

    private List<Movement> captureMovements;

    private boolean canCapture;
    private boolean canPlace;
    private boolean canJump;

    public Piece(String name, int side, int row, int column, List<Movement> normalFirstMovements,List<Movement> normalAnyMovements, List<Movement> captureMovements, boolean canCapture, boolean canPlace, boolean canJump) {
        this.name = name;
        this.side = side;
        position = new Coordinate(column, row);

        this.normalFirstMovements = normalFirstMovements;
        this.normalAnyMovements = normalAnyMovements;
        this.captureMovements = captureMovements;

        this.canCapture = canCapture;
        this.canPlace = canPlace;
        this.canJump = canJump;
    }

    public List<Movement> getNormalFirstMovements (){
        return this.normalFirstMovements;
    }
    public List<Movement> getNormalAnyMovements () {
        return this.normalAnyMovements;
    }
    public List<Movement> getCaptureMovements () {
        return this.captureMovements;
    }
}
