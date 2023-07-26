package com.shank.chess.strategy;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KnightMoveCalculator extends MoveCalculator {

    // TODO should clean up this logic
    @Override
    public List<Coordinate> calculatePotentialMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces) {
        List<Coordinate> calculatedMoves = new ArrayList();

        // offsets from current position
        int[] xs = {-1, -1, 1, 1, -2, -2, 2, 2};
        int[] ys = {2, -2, 2, -2, 1, -1, 1, -1};

        for(int i = 0; i < xs.length; i++){
            Coordinate testCoordinate = new Coordinate(coordinate.getRow() + xs[i], coordinate.getCol() + ys[i]);
            if(pieces.containsKey(testCoordinate) && pieces.get(testCoordinate).isWhitePiece() == pieces.get(coordinate).isWhitePiece()) {
                continue;
            } else {
                calculatedMoves.add(testCoordinate);
            }
        }

        // TODO could be static method in base abstract class
        return calculatedMoves.stream().filter(Coordinate::isWithinBoard).collect(Collectors.toList());
    }
}
