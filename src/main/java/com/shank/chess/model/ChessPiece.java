package com.shank.chess.model;

import com.shank.chess.strategy.*;

public class ChessPiece {
    private String label;
    // PATTERN this is the strategy pattern. A form of composition, which is an alternative to inheritance
    // PATTERN we could have alternatively created subclasses of ChessPiece that had different calculateMoves implementations
    private MoveCalculator moveCalculator;
    private boolean isWhitePiece;

    public ChessPiece(String label) {
        this.isWhitePiece = label.charAt(0) == 'W';
        this.label = label;

        // dynamically assign move calculator for piece
        char pieceType = label.charAt(1);
        this.moveCalculator = switch(pieceType) {
            case 'P' -> new PawnMoveCalculator();
            case 'C' -> new CastleMoveCalculator();
            case 'N' -> new KnightMoveCalculator();
            case 'B' -> new BishopMoveCalculator();
            case 'K' -> new KingMoveCalculator();
            case 'Q' -> new QueenMoveCalculator();
            default -> throw new IllegalArgumentException("Unknown chess piece.");
        };
    }

    public MoveCalculator getMoveCalculator() {
        return moveCalculator;
    }

    public String getLabel() {
        return label;
    }

    public boolean isWhitePiece() {
        return isWhitePiece;
    }
}
