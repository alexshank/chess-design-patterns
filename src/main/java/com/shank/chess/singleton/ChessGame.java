package com.shank.chess.singleton;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;
import com.shank.chess.observer.ChessMovePublisher;
import com.shank.chess.observer.ISubscriber;
import com.shank.chess.strategy.*;

import java.util.*;

public final class ChessGame {

    // TODO this is the singleton pattern
    // TODO this is a naive implementation that is NOT thread safe (no locking, race conditions)
    private static ChessGame instance;

    private Map<Coordinate, ChessPiece> pieces;
    private List<Coordinate> highlights;
    private Optional<Coordinate> selectedCoordinate;

    // TODO we hold a publisher object to orchestrate events to subscribers
    private ChessMovePublisher movePublisher;

    private ChessGame() {
        initializeGame();
    }

    private void initializeGame() {
        this.pieces = new HashMap<>();
        this.highlights = new ArrayList<>();
        this.selectedCoordinate = Optional.empty();
        this.movePublisher = new ChessMovePublisher();

        // TODO this is probably prime use case for abstract factory
        // starting positions for a game of chess
        this.pieces.put(new Coordinate(0, 0), new ChessPiece("WC"));
        this.pieces.put(new Coordinate(0, 1), new ChessPiece("WN"));
        this.pieces.put(new Coordinate(0, 2), new ChessPiece("WB"));
        this.pieces.put(new Coordinate(0, 3), new ChessPiece("WQ"));
        this.pieces.put(new Coordinate(0, 4), new ChessPiece("WK"));
        this.pieces.put(new Coordinate(0, 5), new ChessPiece("WB"));
        this.pieces.put(new Coordinate(0, 6), new ChessPiece("WN"));
        this.pieces.put(new Coordinate(0, 7), new ChessPiece("WC"));
        this.pieces.put(new Coordinate(7, 0), new ChessPiece("BC"));
        this.pieces.put(new Coordinate(7, 1), new ChessPiece("BN"));
        this.pieces.put(new Coordinate(7, 2), new ChessPiece("BB"));
        this.pieces.put(new Coordinate(7, 3), new ChessPiece("BQ"));
        this.pieces.put(new Coordinate(7, 4), new ChessPiece("BK"));
        this.pieces.put(new Coordinate(7, 5), new ChessPiece("BB"));
        this.pieces.put(new Coordinate(7, 6), new ChessPiece("BN"));
        this.pieces.put(new Coordinate(7, 7), new ChessPiece("BC"));
        // pawns
        this.pieces.put(new Coordinate(1, 0), createPawnChessPiece("WP"));
        this.pieces.put(new Coordinate(1, 1), createPawnChessPiece("WP"));
        this.pieces.put(new Coordinate(1, 2), createPawnChessPiece("WP"));
        this.pieces.put(new Coordinate(1, 3), createPawnChessPiece("WP"));
        this.pieces.put(new Coordinate(1, 4), createPawnChessPiece("WP"));
        this.pieces.put(new Coordinate(1, 5), createPawnChessPiece("WP"));
        this.pieces.put(new Coordinate(1, 6), createPawnChessPiece("WP"));
        this.pieces.put(new Coordinate(1, 7), createPawnChessPiece("WP"));
        this.pieces.put(new Coordinate(6, 0), createPawnChessPiece("BP"));
        this.pieces.put(new Coordinate(6, 1), createPawnChessPiece("BP"));
        this.pieces.put(new Coordinate(6, 2), createPawnChessPiece("BP"));
        this.pieces.put(new Coordinate(6, 3), createPawnChessPiece("BP"));
        this.pieces.put(new Coordinate(6, 4), createPawnChessPiece("BP"));
        this.pieces.put(new Coordinate(6, 5), createPawnChessPiece("BP"));
        this.pieces.put(new Coordinate(6, 6), createPawnChessPiece("BP"));
        this.pieces.put(new Coordinate(6, 7), createPawnChessPiece("BP"));
    }

    // add each PawnMoveCalculator to the ChessGame's subscriber list
    // TODO perhaps a better way to avoid this unchecked casting
    private ChessPiece createPawnChessPiece(String label) {
        ChessPiece pawnChessPiece = new ChessPiece(label);
        this.movePublisher.subscribe((ISubscriber<MoveCalculator>) pawnChessPiece.getMoveCalculator());
        return pawnChessPiece;
    }

    // TODO consistent access to a single instance of this class
    public static ChessGame getInstance(){
        if(instance == null){
            instance = new ChessGame();
        }
        return instance;
    }

    public void resetGame(){
        instance.initializeGame();
    }

    public Map<Coordinate, ChessPiece> getPieces() {
        return pieces;
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
                .map(moveCalculator -> moveCalculator.calculatePotentialMoves(selectedCoordinate, this.pieces))
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

        // TODO should check for game end checkmate

        // TODO should handle check against king
    }
}
