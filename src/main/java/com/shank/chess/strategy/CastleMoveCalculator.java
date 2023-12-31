package com.shank.chess.strategy;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;

import java.util.List;
import java.util.Map;

// TODO have not implemented castling
public class CastleMoveCalculator extends MoveCalculator {

    @Override
    public List<Coordinate> calculatePotentialMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces) {
        // to the right, to the left, etc.
        int[] xs = {1, -1, 0, 0};
        int[] ys = {0, 0, -1, 1};
        return MoveCalculator.potentialMovesInSeveralDirections(coordinate, pieces, xs, ys);
    }
}
