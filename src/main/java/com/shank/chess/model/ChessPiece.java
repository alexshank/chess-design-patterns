package com.shank.chess.model;

public class ChessPiece {
    private int row;
    private int col;
    private String label;

    public ChessPiece(int row, int col, String label) {
        this.row = row;
        this.col = col;
        this.label = label;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
