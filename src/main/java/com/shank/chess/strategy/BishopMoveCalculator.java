package com.shank.chess.strategy;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;

import java.util.List;
import java.util.Map;

public class BishopMoveCalculator extends MoveCalculator {

    @Override
    public List<Coordinate> calculatePotentialMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces) {
        // up to the right, up to the left, etc.
        int[] xs = {1, -1, -1, 1};
        int[] ys = {1, 1, -1, -1};
        return MoveCalculator.potentialMovesInSeveralDirections(coordinate, pieces, xs, ys);
    }
}
