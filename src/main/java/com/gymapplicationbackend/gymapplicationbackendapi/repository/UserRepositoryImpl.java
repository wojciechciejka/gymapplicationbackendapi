package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private RedisTemplate redisTemplate;
    private static final String KEY = "USERS";

    @Override
    public boolean addUser(User user) {
        try{
            redisTemplate.opsForHash().put(KEY, user.getUsername(), user);
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
