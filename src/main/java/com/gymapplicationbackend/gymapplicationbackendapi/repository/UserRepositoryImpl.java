package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Trainer;
import com.gymapplicationbackend.gymapplicationbackendapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String PLAYER_ROLE = "PLAYER_ROLE";
    private static final String TRAINER_ROLE = "TRAINER_ROLE";
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String KEY = "USERS";
    private static final String KEY_TRAINER = "TRAINERS";

    @Override
    public boolean addUser(User user) {
        try{
            redisTemplate.opsForHash().put(KEY, user.getUsername(), user);
            if(user.getRole().equals(TRAINER_ROLE)){
                Trainer trainer = new Trainer();
                trainer.setFirstName(user.getFirstName());
                trainer.setLastName(user.getLastName());
                trainer.setFullName(user.getFirstName() + " " + user.getLastName());
                trainer.setUsername(user.getUsername());
                redisTemplate.opsForHash().put(KEY_TRAINER, trainer.getUsername(), trainer);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> users;
        users = redisTemplate.opsForHash().values(KEY);
        return users;
    }
}
