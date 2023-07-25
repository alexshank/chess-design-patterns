package com.shank.chess.model.moveCalculator;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;
import com.shank.chess.model.MoveCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PawnMoveCalculator implements MoveCalculator {

    // TODO we don't have to store this property in our base classes
    // TODO which is one benefit of composition over inheritance
    // TODO we have other move calculators that do not need this field at all
    // TODO even with a very concrete hierarchy of interface/implementation, the implementation makes things tricky
    private boolean firstMoveMade; // pawns can move two squares on their first move
    private boolean willMoveUpwards; // if a white piece, will move "upwards"

    public PawnMoveCalculator(boolean willMoveUpwards) {
        this.willMoveUpwards = willMoveUpwards;
        this.firstMoveMade = false;
    }

    @Override
    public List<Coordinate> calculateMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces) {
        int additive = willMoveUpwards ? 1 : -1;
        List<Coordinate> calculatedMoves = new ArrayList(
                Arrays.asList(new Coordinate(coordinate.getRow() + additive, coordinate.getCol()))
        );
        if(!firstMoveMade){
            calculatedMoves.add(new Coordinate(coordinate.getRow() + 2 * additive, coordinate.getCol()));
        }
        return calculatedMoves.stream().filter(Coordinate::isWithinBoard).collect(Collectors.toList());
    }
}
