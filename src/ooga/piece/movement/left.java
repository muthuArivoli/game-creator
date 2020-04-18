package ooga.piece.movement;

public class left implements Movement {
    private int rangeBegin;
    private int rangeEnd;

    public left(int rangeBegin, int rangeEnd){
        this.rangeBegin = rangeBegin;
        this.rangeEnd = rangeEnd;
    }

}
