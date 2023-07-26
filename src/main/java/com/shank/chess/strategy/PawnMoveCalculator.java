package com.shank.chess.strategy;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;
import com.shank.chess.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO en passant rule has not been implemented
// TODO promotion rule not implemented
public class PawnMoveCalculator extends MoveCalculator implements ISubscriber<MoveCalculator> {

    private boolean firstMoveMade; // pawns can move two squares on their first move

    public PawnMoveCalculator() {
        this.firstMoveMade = false;
    }

    // TODO should clean up this logic
    @Override
    public List<Coordinate> calculatePotentialMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces) {
        int additive = pieces.get(coordinate).isWhitePiece() ? 1 : -1;
        List<Coordinate> calculatedMoves = new ArrayList();

        // moving forward
        Coordinate testCoordinate = new Coordinate(coordinate.getRow() + additive, coordinate.getCol());
        if(!pieces.containsKey(testCoordinate)){
            calculatedMoves.add(testCoordinate);
        }
        testCoordinate = new Coordinate(testCoordinate.getRow() + additive, testCoordinate.getCol());
        if(!firstMoveMade && !pieces.containsKey(testCoordinate)){
            calculatedMoves.add(testCoordinate);
        }

        // moving diagonal to take pieces
        testCoordinate = new Coordinate(coordinate.getRow() + additive, coordinate.getCol() - 1);
        if(pieces.containsKey(testCoordinate) && (pieces.get(testCoordinate).isWhitePiece() != (pieces.get(coordinate).isWhitePiece()))){
            calculatedMoves.add(testCoordinate);
        }
        testCoordinate = new Coordinate(coordinate.getRow() + additive, coordinate.getCol() + 1);
        if(pieces.containsKey(testCoordinate) && (pieces.get(testCoordinate).isWhitePiece() != (pieces.get(coordinate).isWhitePiece()))){
            calculatedMoves.add(testCoordinate);
        }

        return calculatedMoves.stream().filter(Coordinate::isWithinBoard).collect(Collectors.toList());
    }

    // TODO this is the subscriber pattern
    // TODO we turned this move calculator into a subscriber, so it can update it's behavior when needed
    @Override
    public void update(MoveCalculator event) {
        // if we get an event containing this exact move calculator (reference equality),
        // then we know the piece this calculator is associated with has been moved
        // TODO in practice this event object would be much richer
        // TODO this is a hack way of making a subscriber example quickly, you shouldn't rely on ref equality
        // TODO in case the ChessPiece's move calculator object is changed/reassigned after subscription
        if(event == this){
            this.firstMoveMade = true;
        }
    }
}
