package ooga.piece;

import java.util.Objects;

public class Coordinate {
    private int xpos;
    private int ypos;
    public Coordinate(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public int getRow(){
        return xpos;
    }

    public int getCol() {
        return ypos;
    }

    public void setRow(int xpos) {
        this.xpos = xpos;
    }

    public void setCol(int ypos) {
        this.ypos = ypos;
    }


    @Override
    public int hashCode() {
        return Objects.hash(xpos, ypos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return xpos == that.xpos &&
            ypos == that.ypos;
    }
}
