package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TrainerRepositoryImpl implements TrainerRepository{
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String KEY = "TRAINERS";

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers;
        trainers = redisTemplate.opsForHash().values(KEY);
        return trainers;
    }
}
