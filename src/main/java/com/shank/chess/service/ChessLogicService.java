package com.shank.chess.service;

import com.shank.chess.model.ChessGame;
import com.shank.chess.model.Coordinate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO should implement a generic GameLogicService interface
@Service
public class ChessLogicService {

    // TODO can demonstrate the singleton pattern here
    private ChessGame chessGame;

    public ChessGame getNewGame() {
        chessGame = new ChessGame();
        return chessGame;
    }

    public ChessGame handleBoardSquareClick(Coordinate coordinate) {
        // deselect if it has been clicked on twice
        if(chessGame.getSelectedCoordinate().isPresent() && chessGame.getSelectedCoordinate().get().equals(coordinate)) {
            chessGame.setSelectedCoordinate(Optional.empty());
            chessGame.setHighlights(new ArrayList<>());
        }
        // if clicking on a highlighted square, execute a move
        else if(chessGame.getSelectedCoordinate().isPresent() && chessGame.getHighlights().contains(coordinate)){
            chessGame.movePiece(chessGame.getSelectedCoordinate().get(), coordinate);
            chessGame.setSelectedCoordinate(Optional.empty());
            chessGame.setHighlights(new ArrayList<>());
        }
        // clear selected piece if a vacant square is clicked on
        else if(chessGame.isVacantSquare(coordinate)){
            chessGame.setSelectedCoordinate(Optional.empty());
            chessGame.setHighlights(new ArrayList<>());
        }
        // highlight potential moves for a newly selected piece
        else {
            chessGame.setSelectedCoordinate(Optional.of(coordinate));
            List<Coordinate> highlights = chessGame.getPotentialMoves(coordinate);
            chessGame.setHighlights(highlights);
        }

        return chessGame;
    }
}
