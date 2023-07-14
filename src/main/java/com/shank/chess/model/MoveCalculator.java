package com.shank.chess.model;

import java.util.List;

public interface MoveCalculator {
    List<Coordinate> calculateMoves(Coordinate coordinate, List<ChessPiece> pieces);
}
