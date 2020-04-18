package ooga.piece.movement;

import java.util.ArrayList;
import java.util.List;

public class CompositeMovement implements Movement {
    private List<Movement> movements;

    public CompositeMovement(){
        movements = new ArrayList<>();
    }

    public void add(Movement nextMovement){
        movements.add(nextMovement);
    }

    public List<Movement> movements(){
        return this.movements;
    }

}
