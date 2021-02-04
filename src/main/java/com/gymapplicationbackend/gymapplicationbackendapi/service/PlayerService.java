package com.gymapplicationbackend.gymapplicationbackendapi.service;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Player;
import com.gymapplicationbackend.gymapplicationbackendapi.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;


    public List<Player> getAllPlayers() {
        return playerRepository.getAllPlayers();
    }

    public Player getPlayer(String username) {
        return playerRepository.getPlayer(username);
    }

    public boolean setPlayer(Player player) {
        return playerRepository.setPlayer(player);
    }
}
