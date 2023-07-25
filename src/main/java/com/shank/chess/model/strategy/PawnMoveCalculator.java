package com.shank.chess.model.strategy;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;
import com.shank.chess.model.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO en passant rule has not been implemented
// TODO promotion rule not implemented
public class PawnMoveCalculator extends MoveCalculator implements ISubscriber<MoveCalculator> {

    // TODO we don't have to store this property in our base classes
    // TODO which is one benefit of composition over inheritance
    // TODO we have other move calculators that do not need this field at all
    private boolean firstMoveMade; // pawns can move two squares on their first move
    private boolean willMoveUpwards; // if a white piece, will move "upwards"

    public PawnMoveCalculator(boolean willMoveUpwards) {
        this.willMoveUpwards = willMoveUpwards;
        this.firstMoveMade = false;
    }

    // TODO should clean up this logic
    @Override
    public List<Coordinate> calculateMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces) {
        int additive = willMoveUpwards ? 1 : -1;
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

    // TODO we turned this move calculator into a subscriber, so it can update it's behavior when needed
    @Override
    public void update(MoveCalculator event) {
        // if we get an event containing this exact move calculator (reference equality),
        // then we know the piece this calculator is associated with has been moved
        // TODO in practice this event object would be much richer
        // TODO this is a hack way of making a subscriber example quickly, you shouldn't rely on ref equality
        if(event == this){
            this.firstMoveMade = true;
        }
    }
}
