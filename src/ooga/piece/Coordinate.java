package ooga.piece;

import java.util.Objects;

/**
 * Represents a coordinate in the grid
 * @author Muthu Arivoli
 */
public class Coordinate implements Comparable<Coordinate>{
    private int row;
    private int col;
    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }


    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * Checks if this coordinate is equal to another
     * @param o the other coordinate
     * @return whether the coordinates are equal to each other
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return row == that.row &&
            col == that.col;
    }

    public String toString(){
        return row + "" + col;
    }

    @Override
    public int compareTo(Coordinate o) {
        return getCol()+getRow() - (o.getCol()+o.getRow());
    }
}
