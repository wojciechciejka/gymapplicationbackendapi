package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PlayerRepositoryImpl implements PlayerRepository{
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String KEY = "PLAYERS";

    @Override
    public List<Player> getAllPlayers() {
        List<Player> players;
        players = redisTemplate.opsForHash().values(KEY);
        return players;
    }

    @Override
    public Player getPlayer(String username) {
        Player player = (Player) redisTemplate.opsForHash().get(KEY, username);
        return player;
    }

    @Override
    public boolean setPlayer(Player player) {
        redisTemplate.opsForHash().put(KEY, player.getUsername(), player);
        return true;
    }
}
