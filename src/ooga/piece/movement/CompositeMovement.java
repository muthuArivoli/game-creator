package ooga.piece.movement;

import ooga.piece.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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


    @Override
    public List<List<Coordinate>> getValidPaths(Coordinate position, int playerSide, Predicate<Coordinate> checkCoordinatesInBound) {
        List<List<Coordinate>> paths = new ArrayList<>();
        paths.add(new ArrayList<>());
        paths.addAll(movements.get(0).getValidPaths(position,playerSide,checkCoordinatesInBound));
        for(int i=1;i<movements.size();i++){
            List<List<Coordinate>> newPaths = new ArrayList<>();
            for(int k=0;k<paths.size();k++){
                //Coordinate c is the last value in each path (each coordinate list in paths)
                List<List<Coordinate>> pathsToAdd = movements.get(i).getValidPaths(paths.get(k).get(paths.size()-1),playerSide,checkCoordinatesInBound);
                for(int j=0;j<pathsToAdd.size();j++){
                    newPaths.add(new ArrayList<>(paths.get(k)));
                    newPaths.get(newPaths.size()-1).addAll(pathsToAdd.get(j));
                }
            }
            paths = newPaths;
        }
        return paths;
    }
}
