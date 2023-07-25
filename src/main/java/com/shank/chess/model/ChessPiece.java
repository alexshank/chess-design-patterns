package com.shank.chess.model;

import java.util.List;
import java.util.Map;

public class ChessPiece {
    private String label;
    // TODO this is the composite pattern, alternative to inheritance
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

    public void setWhitePiece(boolean whitePiece) {
        isWhitePiece = whitePiece;
    }

    public List<Coordinate> getCandidateMoves(Coordinate selected, Map<Coordinate, ChessPiece> pieces) {
        return this.moveCalculator.calculateMoves(selected, pieces);
    }
}
