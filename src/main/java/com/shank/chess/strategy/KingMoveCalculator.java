package com.shank.chess.strategy;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO have not implemented castling
public class KingMoveCalculator extends MoveCalculator {

    @Override
    public List<Coordinate> calculatePotentialMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces) {
        // to the right, to the left, etc.
        int[] xs = {1, -1, 0, 0, 1, -1, -1, 1};
        int[] ys = {0, 0, -1, 1, 1, 1, -1, -1};

        // same moves as queen, but then we filter to only adjacent squares
        return MoveCalculator.potentialMovesInSeveralDirections(coordinate, pieces, xs, ys)
                .stream()
                .filter(c -> Math.abs(c.getRow() - coordinate.getRow()) < 2 && Math.abs(c.getCol() - coordinate.getCol()) < 2)
                .collect(Collectors.toList());
    }
}
