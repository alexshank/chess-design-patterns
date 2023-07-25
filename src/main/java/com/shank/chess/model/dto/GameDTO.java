package com.shank.chess.model.dto;

import com.shank.chess.model.ChessGame;
import com.shank.chess.model.Coordinate;

import java.util.List;
import java.util.stream.Collectors;

public class GameDTO {
    private List<PieceDTO> pieces;
    private List<Coordinate> highlights;
    private Coordinate selected;

    public GameDTO(ChessGame chessGame) {
        this.highlights = chessGame.getHighlights();
        this.selected = chessGame.getSelectedCoordinate().orElseGet(() -> null);
        // TODO is this best way? Maybe mappers?
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
}
