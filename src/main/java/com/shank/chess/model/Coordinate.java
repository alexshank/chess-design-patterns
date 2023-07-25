package com.shank.chess.model;

public class Coordinate {

    private static int COL_COUNT = 8;
    private static int ROW_COUNT = 8;

    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int hashCode()
    {
        // rudimentary hash function that will serve our purposes
        // needed so we can use Coordinates as keys in a map data structure
        return this.row * 10 + this.col;
    }

    @Override
    public boolean equals(Object object)
    {
        Coordinate other = (Coordinate) object;
        return this.row == other.getRow() && this.col == other.getCol();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isWithinBoard(){
        return this.row < ROW_COUNT && this.row >= 0 && this.col < COL_COUNT && this.col >= 0;
    }
}
