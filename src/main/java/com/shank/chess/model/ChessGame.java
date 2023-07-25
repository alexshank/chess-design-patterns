package com.shank.chess.model;

import com.shank.chess.model.strategy.PawnMoveCalculator;
import com.shank.chess.observer.ChessMovePublisher;

import java.util.*;

// TODO could demo builder pattern with this class
public class ChessGame {

    private Map<Coordinate, ChessPiece> pieces;
    private List<Coordinate> highlights;
    private Optional<Coordinate> selectedCoordinate;
    private ChessMovePublisher movePublisher;

    public ChessGame() {
        this.pieces = new HashMap<>();
        this.highlights = new ArrayList<>();
        this.selectedCoordinate = Optional.empty();
        this.movePublisher = new ChessMovePublisher();

        // starting positions for a game of chess
        this.pieces.put(new Coordinate(0, 0), new ChessPiece("WC", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(0, 1), new ChessPiece("WN", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(0, 2), new ChessPiece("WB", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(0, 3), new ChessPiece("WQ", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(0, 4), new ChessPiece("WK", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(0, 5), new ChessPiece("WB", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(0, 6), new ChessPiece("WN", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(0, 7), new ChessPiece("WC", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(7, 0), new ChessPiece("BC", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(7, 1), new ChessPiece("BN", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(7, 2), new ChessPiece("BB", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(7, 3), new ChessPiece("BQ", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(7, 4), new ChessPiece("BK", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(7, 5), new ChessPiece("BB", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(7, 6), new ChessPiece("BN", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(7, 7), new ChessPiece("BC", new PawnMoveCalculator(false)));
        // pawns
        this.pieces.put(new Coordinate(1, 0), new ChessPiece("WP", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(1, 1), new ChessPiece("WP", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(1, 2), new ChessPiece("WP", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(1, 3), new ChessPiece("WP", new PawnMoveCalculator(true)));
        PawnMoveCalculator tempMoveCalculator = new PawnMoveCalculator(true);
        this.movePublisher.subscribe(tempMoveCalculator);
        this.pieces.put(new Coordinate(1, 4), new ChessPiece("WP", tempMoveCalculator));
        this.pieces.put(new Coordinate(1, 5), new ChessPiece("WP", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(1, 6), new ChessPiece("WP", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(1, 7), new ChessPiece("WP", new PawnMoveCalculator(true)));
        this.pieces.put(new Coordinate(6, 0), new ChessPiece("BP", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(6, 1), new ChessPiece("BP", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(6, 2), new ChessPiece("BP", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(6, 3), new ChessPiece("BP", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(6, 4), new ChessPiece("BP", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(6, 5), new ChessPiece("BP", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(6, 6), new ChessPiece("BP", new PawnMoveCalculator(false)));
        this.pieces.put(new Coordinate(6, 7), new ChessPiece("BP", new PawnMoveCalculator(false)));
    }

    public Map<Coordinate, ChessPiece> getPieces() {
        return pieces;
    }

    public void setPieces(Map<Coordinate, ChessPiece> pieces) {
        this.pieces = pieces;
    }

    public List<Coordinate> getHighlights() {
        return highlights;
    }

    public Optional<Coordinate> getSelectedCoordinate() {
        return selectedCoordinate;
    }

    public void setSelectedCoordinate(Optional<Coordinate> selectedCoordinate) {
        this.selectedCoordinate = selectedCoordinate;
    }

    public void setHighlights(List<Coordinate> highlights) {
        this.highlights = highlights;
    }

    public boolean isVacantSquare(Coordinate coordinate) {
        return !this.pieces.containsKey(coordinate);
    }

    public List<Coordinate> getPotentialMoves(Coordinate selectedCoordinate) {
        return Optional.ofNullable(pieces.get(selectedCoordinate))
                .map(ChessPiece::getMoveCalculator)
                .map(moveCalculator -> moveCalculator.calculateMoves(selectedCoordinate, this.pieces))
                .orElseGet(() -> Collections.emptyList());
    }

    public void movePiece(Coordinate start, Coordinate end){
        // perform movement
        ChessPiece movingPiece = this.getPieces().get(start);
        this.getPieces().remove(start);
        this.getPieces().remove(end);
        this.getPieces().put(end, movingPiece);

        // publish event saying a piece was moved
        this.movePublisher.notifySubscribers(movingPiece.getMoveCalculator());
    }
}
