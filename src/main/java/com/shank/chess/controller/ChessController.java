package com.shank.chess.controller;

import com.shank.chess.model.Coordinate;
import com.shank.chess.model.dto.GameDTO;
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
    public GameDTO newGame() {
        return new GameDTO(chessLogicService.getNewGame());
    }

    @GetMapping("/square")
    public GameDTO candidates(@RequestParam int row, @RequestParam int col) {
        return new GameDTO(chessLogicService.handleBoardSquareClick(new Coordinate(row, col)));
    }

    @GetMapping("/refresh")
    public GameDTO refresh() {
        return new GameDTO(chessLogicService.getCurrentGame());
    }
}