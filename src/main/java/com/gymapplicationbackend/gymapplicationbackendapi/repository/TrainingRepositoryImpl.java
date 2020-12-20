package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Repository
public class TrainingRepositoryImpl implements TrainingRepository{

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
    public boolean addUserTraining(String username, Training training) {
        try{
            redisTemplate.opsForHash().put(username + "_" + KEY, training.getDate(), training);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUserTraining(String username, long date) {
        try{
            Set<Training> set = redisTemplate.opsForHash().keys(username + "_" + KEY);
            Long result = redisTemplate.opsForHash().delete(username + "_" + KEY, date);
//            .set("key", "", 1, TimeUnit.MILLISECONDS);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
