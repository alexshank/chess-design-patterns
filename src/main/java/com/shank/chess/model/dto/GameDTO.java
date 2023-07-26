package com.shank.chess.model.dto;

import com.shank.chess.singleton.ChessGame;
import com.shank.chess.model.Coordinate;

import java.util.List;
import java.util.stream.Collectors;

public class GameDTO {
    private List<PieceDTO> pieces;
    private List<Coordinate> highlights;
    private Coordinate selected;
    private boolean whiteToMove;

    public GameDTO(ChessGame chessGame) {
        this.highlights = chessGame.getHighlights();
        this.selected = chessGame.getSelectedCoordinate().orElseGet(() -> null);
        this.whiteToMove = chessGame.isWhiteToMove();

        // PATTERN this implicitly is using the iterator pattern to go through a collection we don't know the implementation of
        this.pieces = chessGame.getPieces().entrySet().stream()
                .map(entry -> new PieceDTO(entry.getValue().getLabel(), entry.getKey().getRow(), entry.getKey().getCol()))
                .collect(Collectors.toList());
    }

    public List<PieceDTO> getPieces() {
        return pieces;
    }

    public void setPieces(List<PieceDTO> pieces) {
        this.pieces = pieces;
    }

    public List<Coordinate> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<Coordinate> highlights) {
        this.highlights = highlights;
    }

    public Coordinate getSelected() {
        return selected;
    }

    public void setSelected(Coordinate selected) {
        this.selected = selected;
    }

    public boolean isWhiteToMove() {
        return whiteToMove;
    }

    public void setWhiteToMove(boolean whiteToMove) {
        this.whiteToMove = whiteToMove;
    }
}
