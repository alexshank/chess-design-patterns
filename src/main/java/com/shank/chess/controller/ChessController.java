package com.shank.chess.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChessController {

    @GetMapping("/api/ping")
    public String ping() {
        System.out.println("Got ping request.");
        return "Service is healthy.";
    }
}