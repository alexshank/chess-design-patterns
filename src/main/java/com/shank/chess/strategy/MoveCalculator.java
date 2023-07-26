package com.shank.chess.strategy;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// contains base utility methods used by most move calculators
public abstract class MoveCalculator {

    // follow a line away from a piece until you hit another piece
    // (x, y) defines a vector / direction to follow. e.g. (1, 0) is right direction
    private static List<Coordinate> potentialMovesInDirection(Coordinate start, Map<Coordinate, ChessPiece> pieces, int x, int y){
        Coordinate testCoordinate = start.copy();   // TODO this is the prototype pattern
        boolean isWhitePiece = pieces.get(start).isWhitePiece();

        List<Coordinate> result = new ArrayList<>();
        while(true){
            testCoordinate = new Coordinate(testCoordinate.getRow() + x, testCoordinate.getCol() + y);

            boolean isWithinBoard = testCoordinate.isWithinBoard();
            boolean isVacantSquare = !pieces.containsKey(testCoordinate);
            boolean containsFriendlyPiece = !isVacantSquare && pieces.get(testCoordinate).isWhitePiece() == isWhitePiece;

            // no longer in board, or ran into friendly piece
            if(!isWithinBoard || containsFriendlyPiece){
                break;
            }

            // enemy piece. valid destination, but ends looping
            if(!isVacantSquare){
                result.add(testCoordinate);
                break;
            }

            result.add(testCoordinate);
        }

        return result;
    }

    // apply potentialMovesInDirection in multiple direction
    public static List<Coordinate> potentialMovesInSeveralDirections(Coordinate start, Map<Coordinate, ChessPiece> pieces, int[] xs, int[] ys) {
        List<Coordinate> calculatedMoves = new ArrayList<>();
        for(int i = 0; i < xs.length; i++) {
            calculatedMoves.addAll(MoveCalculator.potentialMovesInDirection(start, pieces, xs[i], ys[i]));
        }
        return calculatedMoves;
    }

    public abstract List<Coordinate> calculatePotentialMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces);
}
