package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TrainingRepositoryImpl implements TrainingRepository {

    @Autowired
    private RedisTemplate redisTemplate;
    private static final String KEY = "TRAININGS";

    @Override
    public List<Training> getUserTrainings(String username) {
        List<Training> trainings;
        trainings = redisTemplate.opsForHash().values(username + "_" + KEY);
        return trainings;
    }

    @Override
    public boolean addUserTraining(Training training) {
        try {
            redisTemplate.opsForHash().put(training.getPlayerUsername() + "_" + KEY, training.getDate(), training);
            redisTemplate.opsForHash().put(training.getTrainerUsername() + "_" + KEY, training.getDate(), training);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUserTraining(String playerUsername, String trainerUsername, long date) {
        try {
            Long result = redisTemplate.opsForHash().delete(playerUsername + "_" + KEY, date);
            result = redisTemplate.opsForHash().delete(trainerUsername + "_" + KEY, date);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
