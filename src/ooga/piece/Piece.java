package ooga.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import ooga.piece.movement.Movement;

public class Piece {
    private String name;
    private int side;
    private Coordinate position;

    private List<Movement> normalFirstMovements;
    private List<Movement> normalAnyMovements;

    private List<Movement> captureMovements;

    private boolean canJump;
    private boolean canCaptureJump;
    private int pointValue;

    public Piece(String name, int side, int row, int column, List<Movement> normalFirstMovements,List<Movement> normalAnyMovements, List<Movement> captureMovements,boolean canJump, boolean canCaptureJump) {
        this.name = name;
        this.side = side == 2 ? -1 : 1;
        position = new Coordinate(row, column);

        this.normalFirstMovements = normalFirstMovements;
        this.normalAnyMovements = normalAnyMovements;
        this.captureMovements = captureMovements;

        this.canJump = canJump;
        this.canCaptureJump = canCaptureJump;
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

    public List<List<Coordinate>> getValidPaths(Coordinate c, int playerSide, Predicate<Coordinate> checkCoordinateInBounds){
        List<List<Coordinate>> validPaths = new ArrayList<>();
        for(Movement m:normalAnyMovements){
            validPaths.addAll(m.getValidPaths(c,playerSide,checkCoordinateInBounds));
        }
        return validPaths;
    }

    public int getSide() {
        return side;
    }


    public boolean isCanJump() {
        return canJump;
    }

    public boolean isCanCaptureJump () {
        return canCaptureJump;
    }

    public String getPieceName() {return name;}

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition (int x, int y) {
        position = new Coordinate(x, y);
    }
}
