package com.shank.chess.model;

import java.util.Arrays;
import java.util.List;

public class PawnMoveCalculator implements MoveCalculator {
    @Override
    public List<Coordinate> calculateMoves(Coordinate coordinate, List<ChessPiece> pieces) {
        // TODO actually implement
        return Arrays.asList(new Coordinate(coordinate.getRow() + 1, coordinate.getCol()));
    }
}
