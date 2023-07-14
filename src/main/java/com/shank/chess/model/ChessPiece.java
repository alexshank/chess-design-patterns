package com.shank.chess.model;

import java.util.List;

public class ChessPiece {
    private int row;
    private int col;
    private String label;
    // TODO this is the composite pattern, alternative to inheritance
    private MoveCalculator moveCalculator;

    public ChessPiece(int row, int col, String label, MoveCalculator moveCalculator) {
        this.row = row;
        this.col = col;
        this.label = label;
        this.moveCalculator = moveCalculator;
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

    public List<Coordinate> getCandidateMoves(List<ChessPiece> pieces) {
        return this.moveCalculator.calculateMoves(new Coordinate(this.row, this.col), pieces);
    }
}
