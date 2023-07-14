package com.shank.chess.service;

import com.shank.chess.model.ChessGame;
import com.shank.chess.model.Coordinate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

// TODO should implement a generic GameLogicService interface
@Service
public class ChessLogicService {

    // TODO can demonstrate the singleton pattern here
    private ChessGame chessGame;

    public ChessGame getNewGame() {
        chessGame = new ChessGame();
        return chessGame;
    }

    public ChessGame getCandidateMoves(int row, int col) {
        // do nothing if a vacant square is clicked on
        if(chessGame.checkVacantSquare(new Coordinate(row, col))){
            return chessGame;
        }
        // deselect if this is the second click on the square
        else if(chessGame.getSelected() != null && chessGame.getSelected().getRow() == row && chessGame.getSelected().getCol() == col){
            chessGame.setSelected(null);
            chessGame.setHighlights(new ArrayList<>());
        }else{
            chessGame.setHighlights(Arrays.asList(new Coordinate(5, 5)));
            chessGame.setSelected(new Coordinate(row, col));
        }

        return chessGame;
    }
}
