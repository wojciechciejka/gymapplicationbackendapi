package com.gymapplicationbackend.gymapplicationbackendapi.controller;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Message;
import com.gymapplicationbackend.gymapplicationbackendapi.model.UserCorespondant;
import com.gymapplicationbackend.gymapplicationbackendapi.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping(value = "/getAllAvaiableCorespondents")
    public ResponseEntity<List<UserCorespondant>> getAllAvaiableCorespondents() {
        List<UserCorespondant> userCorespondants;
        userCorespondants = chatService.getAllAvaiableCorespondents();
        return ResponseEntity.ok(userCorespondants);
    }

    @GetMapping(value = "/getUserCorespondents")
    public ResponseEntity<List<UserCorespondant>> getUserCorespondents(@RequestParam("username") String username) {
        List<UserCorespondant> userCorespondants;
        userCorespondants = chatService.getUserCorespondents(username);
        return ResponseEntity.ok(userCorespondants);
    }

    @PostMapping(value = "/addNewUserCorespondant")
    public ResponseEntity<Boolean> addNewUserCorespondant(@RequestParam("username") String username, @RequestBody UserCorespondant userCorespondant) {
        boolean value = chatService.addNewUserCorespondant(username, userCorespondant);
        return ResponseEntity.ok(value);
    }

    @PostMapping(value = "/addNewMessage")
    public ResponseEntity<Boolean> addNewMessage(@RequestParam("senderUsername") String senderUsername, @RequestParam("receiverUsername") String receiverUsername, @RequestBody Message message) {
        boolean value = chatService.addNewMessage(senderUsername, receiverUsername, message);
        return ResponseEntity.ok(value);
    }

    @GetMapping(value = "/getAllMessages")
    public ResponseEntity<List<Message>> getAllMessages(@RequestParam("senderUsername") String senderUsername, @RequestParam("receiverUsername") String receiverUsername) {
        List<Message> messages = chatService.getAllMessages(senderUsername, receiverUsername);
        return ResponseEntity.ok(messages);
    }
}
