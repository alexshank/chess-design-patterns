package com.shank.chess.model.strategy;

import com.shank.chess.model.ChessPiece;
import com.shank.chess.model.Coordinate;

import java.util.List;
import java.util.Map;

public interface MoveCalculator {
    List<Coordinate> calculateMoves(Coordinate coordinate, Map<Coordinate, ChessPiece> pieces);
}
