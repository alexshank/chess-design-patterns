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

    public ChessGame getCandidateMoves(Coordinate coordinate) {
        // do nothing if a vacant square is clicked on
        if(chessGame.isVacantSquare(coordinate)){
            chessGame.setSelected(Optional.empty());
            chessGame.setHighlights(new ArrayList<>());
        }
        // deselect if this is the second click on the square
        else if(chessGame.getSelected().isPresent() && chessGame.getSelected().get().equals(coordinate)) {
            chessGame.setSelected(Optional.empty());
            chessGame.setHighlights(new ArrayList<>());
        }
        // there may actually be potential moves
        else {
            List<Coordinate> highlights = chessGame.getPotentialMoves(coordinate);
            chessGame.setHighlights(highlights);
            chessGame.setSelected(Optional.of(coordinate));
        }

        return chessGame;
    }
}
