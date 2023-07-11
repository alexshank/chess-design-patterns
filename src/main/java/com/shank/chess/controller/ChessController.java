package com.shank.chess.controller;

import com.shank.chess.model.ChessGame;
import com.shank.chess.service.ChessLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}