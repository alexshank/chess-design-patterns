package com.shank.chess.model;

import java.util.List;
import java.util.Map;

public interface MoveCalculator {
    List<Coordinate> calculateMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces);
}
