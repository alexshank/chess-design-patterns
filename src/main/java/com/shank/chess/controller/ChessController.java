package com.shank.chess.controller;

import com.shank.chess.model.ChessGame;
import com.shank.chess.service.ChessLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ChessController {

    @Autowired
    private ChessLogicService chessLogicService;

    @GetMapping("/ping")
    public String ping() {
        return "Service is healthy.";
    }

    @GetMapping("/new")
    public ChessGame newGame() {
        return chessLogicService.getNewGame();
    }

    @GetMapping("/candidates")
    public ChessGame newGame(@RequestParam int row, @RequestParam int col) {
        return chessLogicService.getCandidateMoves(row, col);
    }
}