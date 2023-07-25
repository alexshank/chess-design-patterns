package com.shank.chess.model.strategy;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;

import java.util.List;
import java.util.Map;

public class QueenMoveCalculator extends MoveCalculator {

    @Override
    public List<Coordinate> calculateMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces) {
        // to the right, to the left, etc.
        int[] xs = {1, -1, 0, 0, 1, -1, -1, 1};
        int[] ys = {0, 0, -1, 1, 1, 1, -1, -1};
        return MoveCalculator.movesInSeveralDirections(coordinate, pieces, xs, ys);
    }
}
