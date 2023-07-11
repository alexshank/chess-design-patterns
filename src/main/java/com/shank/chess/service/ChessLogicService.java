package com.shank.chess.service;

import com.shank.chess.model.ChessGame;
import org.springframework.stereotype.Service;

// TODO should implement a generic GameLogicService interface
@Service
public class ChessLogicService {

    public ChessGame getNewGame() {
        return new ChessGame();
    }

}
