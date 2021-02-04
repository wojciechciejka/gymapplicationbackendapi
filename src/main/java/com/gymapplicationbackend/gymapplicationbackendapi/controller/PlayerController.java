package com.gymapplicationbackend.gymapplicationbackendapi.controller;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Player;
import com.gymapplicationbackend.gymapplicationbackendapi.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/getAllPlayers")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players;
        players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping(value = "/getPlayer")
    public ResponseEntity<Player> getPlayer(@RequestParam("username") String username) {
        Player player;
        player = playerService.getPlayer(username);
        return ResponseEntity.ok(player);
    }

    @PostMapping(value = "/setPlayer")
    public ResponseEntity<String> setPlayer(@RequestBody Player player) {
        boolean result = playerService.setPlayer(player);
        if (result) {
            return ResponseEntity.ok("Player set Successfully !!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
