package com.shank.chess.service;

import com.shank.chess.singleton.ChessGame;
import com.shank.chess.model.Coordinate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class ChessLogicService {

    // PATTERN this is the singleton pattern
    private ChessGame chessGame = ChessGame.getInstance();

    public ChessGame getNewGame() {
        chessGame.resetGame();
        return chessGame;
    }

    // TODO should clean up this logic
    public ChessGame handleBoardSquareClick(Coordinate coordinate) {
        // do nothing if a piece that cannot be moved (not its turn) is clicked
        if(chessGame.getPieces().get(coordinate) != null && chessGame.getPieces().get(coordinate).isWhitePiece() != chessGame.isWhiteToMove()) {
            return chessGame;
        }

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
            chessGame.setWhiteToMove(!chessGame.isWhiteToMove());
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

    public ChessGame getCurrentGame() {
        return this.chessGame;
    }
}
