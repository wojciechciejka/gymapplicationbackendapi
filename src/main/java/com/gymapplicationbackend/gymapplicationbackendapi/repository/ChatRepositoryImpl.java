package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Message;
import com.gymapplicationbackend.gymapplicationbackendapi.model.User;
import com.gymapplicationbackend.gymapplicationbackendapi.model.UserCorespondant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Repository
public class ChatRepositoryImpl implements ChatRepository {


    @Autowired
    private RedisTemplate redisTemplate;
    private static final String KEY_CORESPONDENTS = "_CORESPONDENTS";
    private static final String KEY_USERS = "USERS";
    private static final String KEY_MESSAGES = "MESSAGES";
    private static final String KEY_SEND_TO = "_SEND_TO_";
    private static final String KEY_RECEIVE_FROM_ = "_RECEIVE_FROM_";

    @Override
    public List<UserCorespondant> getAllAvaiableCorespondents() {
        List<User> users;
        users = redisTemplate.opsForHash().values(KEY_USERS);
        List<UserCorespondant> userCorespondants = new ArrayList<>();
        for (User u : users) {
            UserCorespondant userCorespondant = new UserCorespondant();
            userCorespondant.setFullName(u.getFirstName() + " " + u.getLastName());
            userCorespondant.setUsername(u.getUsername());
            userCorespondant.setRole(u.getRole());
            userCorespondants.add(userCorespondant);
        }
        return userCorespondants;
    }

    @Override
    public boolean addNewUserCorespondant(String username, UserCorespondant userCorespondant2) {
        User user;
        user = (User) redisTemplate.opsForHash().get(KEY_USERS, username);
        UserCorespondant userCorespondant1 = new UserCorespondant();
        userCorespondant1.setFullName(user.getFirstName() + " " + user.getLastName());
        userCorespondant1.setUsername(username);
        userCorespondant1.setRole(user.getRole());
        redisTemplate.opsForHash().put(userCorespondant2.getUsername() + KEY_CORESPONDENTS, userCorespondant1.getUsername(), userCorespondant1);
        redisTemplate.opsForHash().put(userCorespondant1.getUsername() + KEY_CORESPONDENTS, userCorespondant2.getUsername(), userCorespondant2);
        return true;
    }

    @Override
    public List<UserCorespondant> getUserCorespondents(String username) {
        List<UserCorespondant> userCorespondants;
        userCorespondants = redisTemplate.opsForHash().values(username + KEY_CORESPONDENTS);
        return userCorespondants;
    }

    @Override
    public boolean addNewMessage(String senderUsername, String receiverUsername, Message message) {
        UserCorespondant userCorespondant = (UserCorespondant) redisTemplate.opsForHash().get(receiverUsername + KEY_CORESPONDENTS, senderUsername);
        userCorespondant.setNewNotification(true);
        redisTemplate.opsForHash().put(receiverUsername + KEY_CORESPONDENTS, senderUsername, userCorespondant);
        redisTemplate.opsForHash().put(senderUsername + KEY_SEND_TO + receiverUsername, message.getDate(), message);
        redisTemplate.opsForHash().put(receiverUsername + KEY_RECEIVE_FROM_ + senderUsername, message.getDate(), message);
        return true;
    }

    @Override
    public List<Message> getAllMessages(String senderUsername, String receiverUsername) {
        UserCorespondant userCorespondant = (UserCorespondant) redisTemplate.opsForHash().get(senderUsername + KEY_CORESPONDENTS, receiverUsername);
        userCorespondant.setNewNotification(false);
        redisTemplate.opsForHash().put(senderUsername + KEY_CORESPONDENTS, receiverUsername, userCorespondant);
        List<Message> senderMessages;
        senderMessages = redisTemplate.opsForHash().values(senderUsername + KEY_SEND_TO + receiverUsername);
        List<Message> receiverMessages;
        receiverMessages = redisTemplate.opsForHash().values(senderUsername + KEY_RECEIVE_FROM_ + receiverUsername);

        List<Message> allMessages = Stream.concat(senderMessages.stream(), receiverMessages.stream())
                .collect(Collectors.toList());

        Collections.sort(allMessages, new Comparator<Message>() {
            public int compare(Message m1, Message m2) {
                if (m1.getDate() == null || m2.getDate() == null)
                    return 0;
                return Long.compare(m1.getDate(), m2.getDate());
            }
        });
        return allMessages;
    }
}
