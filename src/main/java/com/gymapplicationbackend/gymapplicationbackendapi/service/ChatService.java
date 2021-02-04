package com.gymapplicationbackend.gymapplicationbackendapi.service;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Message;
import com.gymapplicationbackend.gymapplicationbackendapi.model.UserCorespondant;
import com.gymapplicationbackend.gymapplicationbackendapi.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public List<UserCorespondant> getAllAvaiableCorespondents() {
        return chatRepository.getAllAvaiableCorespondents();
    }

    public boolean addNewUserCorespondant(String username, UserCorespondant userCorespondant) {
        return chatRepository.addNewUserCorespondant(username, userCorespondant);
    }

    public List<UserCorespondant> getUserCorespondents(String username) {
        return chatRepository.getUserCorespondents(username);
    }

    public boolean addNewMessage(String senderUsername, String receiverUsername, Message message) {
        return chatRepository.addNewMessage(senderUsername, receiverUsername, message);
    }

    public List<Message> getAllMessages(String senderUsername, String receiverUsername) {
        return chatRepository.getAllMessages(senderUsername, receiverUsername);
    }
}
