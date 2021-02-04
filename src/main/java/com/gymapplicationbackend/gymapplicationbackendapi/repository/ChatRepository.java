package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Message;
import com.gymapplicationbackend.gymapplicationbackendapi.model.UserCorespondant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository {
    List<UserCorespondant> getAllAvaiableCorespondents();

    boolean addNewUserCorespondant(String username, UserCorespondant userCorespondant);

    List<UserCorespondant> getUserCorespondents(String username);

    boolean addNewMessage(String senderUsername, String receiverUsername, Message message);

    List<Message> getAllMessages(String senderUsername, String receiverUsername);
}
