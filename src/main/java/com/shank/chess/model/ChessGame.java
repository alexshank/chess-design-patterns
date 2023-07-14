package com.shank.chess.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO could demo builder pattern with this class
public class ChessGame {

    private List<ChessPiece> pieces;
    private List<Coordinate> highlights;
    private Coordinate selected;

    public ChessGame() {
        this.pieces = new ArrayList<>(
                Arrays.asList(
                        // non-pawns
                        new ChessPiece(0, 0, "WC", new PawnMoveCalculator()),
                        new ChessPiece(0, 1, "WN", new PawnMoveCalculator()),
                        new ChessPiece(0, 2, "WB", new PawnMoveCalculator()),
                        new ChessPiece(0, 3, "WQ", new PawnMoveCalculator()),
                        new ChessPiece(0, 4, "WK", new PawnMoveCalculator()),
                        new ChessPiece(0, 5, "WB", new PawnMoveCalculator()),
                        new ChessPiece(0, 6, "WN", new PawnMoveCalculator()),
                        new ChessPiece(0, 7, "WC", new PawnMoveCalculator()),
                        new ChessPiece(7, 0, "BC", new PawnMoveCalculator()),
                        new ChessPiece(7, 1, "BN", new PawnMoveCalculator()),
                        new ChessPiece(7, 2, "BB", new PawnMoveCalculator()),
                        new ChessPiece(7, 3, "BQ", new PawnMoveCalculator()),
                        new ChessPiece(7, 4, "BK", new PawnMoveCalculator()),
                        new ChessPiece(7, 5, "BB", new PawnMoveCalculator()),
                        new ChessPiece(7, 6, "BN", new PawnMoveCalculator()),
                        new ChessPiece(7, 7, "BC", new PawnMoveCalculator()),
                        // pawns
                        new ChessPiece(1, 0, "WP", new PawnMoveCalculator()),
                        new ChessPiece(1, 1, "WP", new PawnMoveCalculator()),
                        new ChessPiece(1, 2, "WP", new PawnMoveCalculator()),
                        new ChessPiece(1, 3, "WP", new PawnMoveCalculator()),
                        new ChessPiece(1, 4, "WP", new PawnMoveCalculator()),
                        new ChessPiece(1, 5, "WP", new PawnMoveCalculator()),
                        new ChessPiece(1, 6, "WP", new PawnMoveCalculator()),
                        new ChessPiece(1, 7, "WP", new PawnMoveCalculator()),
                        new ChessPiece(6, 0, "BP", new PawnMoveCalculator()),
                        new ChessPiece(6, 1, "BP", new PawnMoveCalculator()),
                        new ChessPiece(6, 2, "BP", new PawnMoveCalculator()),
                        new ChessPiece(6, 3, "BP", new PawnMoveCalculator()),
                        new ChessPiece(6, 4, "BP", new PawnMoveCalculator()),
                        new ChessPiece(6, 5, "BP", new PawnMoveCalculator()),
                        new ChessPiece(6, 6, "BP", new PawnMoveCalculator()),
                        new ChessPiece(6, 7, "BP", new PawnMoveCalculator())
                    )
        );
    }

    public Coordinate getSelected() {
        return selected;
    }

    public void setSelected(Coordinate selected) {
        this.selected = selected;
    }

    public List<ChessPiece> getPieces() {
        return pieces;
    }

    public void setPieces(List<ChessPiece> pieces) {
        this.pieces = pieces;
    }

    public List<Coordinate> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<Coordinate> highlights) {
        this.highlights = highlights;
    }

    public boolean checkVacantSquare(Coordinate coord) {
        // TODO This is the iterator pattern
        for(ChessPiece piece : this.pieces){
            if(piece.getRow() == coord.getRow() && piece.getCol() == coord.getCol()){
                return false;
            }
        }
        return true;
    }

    // TODO this assumes (safely for now) that the given coordinate has a piece on it
    public List<Coordinate> getPotentialMoves(Coordinate coord) {
        System.out.println(coord.getCol());
        System.out.println(coord.getRow());

        ChessPiece selected = this.pieces
                .stream()
                .filter(piece -> {

                    System.out.println(piece.getCol());
                    System.out.println(piece.getRow());
                    return piece.getRow() == coord.getRow() && piece.getCol() == coord.getCol();})
                .findFirst()
                .get();

        return selected.getCandidateMoves(this.pieces);

    }
}
