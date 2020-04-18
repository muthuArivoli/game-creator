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

    private boolean canPlace;

    public Piece(String name, int side, int row, int column, List<Movement> normalFirstMovements,List<Movement> normalAnyMovements, List<Movement> captureMovements, boolean canPlace) {
        this.name = name;
        this.side = side;
        position = new Coordinate(column, row);

        this.normalFirstMovements = normalFirstMovements;
        this.normalAnyMovements = normalAnyMovements;
        this.captureMovements = captureMovements;

        this.canPlace = canPlace;
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


    public boolean isCanPlace () {
        return canPlace;
    }

}
