package com.shank.chess.model.dto;


public class PieceDTO {
    private String label;
    private int row;
    private int col;

    public PieceDTO(String label, int row, int col) {
        this.label = label;
        this.row = row;
        this.col = col;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
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
}
