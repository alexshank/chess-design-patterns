package com.shank.chess.model;

import com.shank.chess.strategy.MoveCalculator;

public class ChessPiece {
    private String label;
    // TODO this is the strategy pattern. A form a composition, which is an alternative to inheritance
    private MoveCalculator moveCalculator;
    private boolean isWhitePiece;

    public ChessPiece(String label, MoveCalculator moveCalculator) {
        this.isWhitePiece = label.charAt(0) == 'W';
        this.label = label;
        this.moveCalculator = moveCalculator;
    }

    public MoveCalculator getMoveCalculator() {
        return moveCalculator;
    }

    public void setMoveCalculator(MoveCalculator moveCalculator) {
        this.moveCalculator = moveCalculator;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isWhitePiece() {
        return isWhitePiece;
    }
}
