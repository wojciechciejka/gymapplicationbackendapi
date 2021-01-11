package com.gymapplicationbackend.gymapplicationbackendapi.controller;

import com.gymapplicationbackend.gymapplicationbackendapi.model.NewUser;
import com.gymapplicationbackend.gymapplicationbackendapi.model.User;
import com.gymapplicationbackend.gymapplicationbackendapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()90_-+=|?<>";
    static SecureRandom rnd = new SecureRandom();



    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody NewUser newUser){
        User user = createNewUser(newUser);
        boolean result = userService.saveUser(user);
        if(result){
            return ResponseEntity.ok("User Created Successfully !!");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private User createNewUser(NewUser newUser) {
        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmailId(newUser.getEmailId());
        user.setUsername(newUser.getUsername());
        user.setPassword_salt(randomString());
        user.setRole(newUser.getRole());
        user.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword() + user.getPassword_salt()));
        user.setAge(newUser.getAge());
        return user;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> fetchAllUser(){
        List<User> users;
        users = userService.fetchAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam("username") String username){
        User user;
        user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    private String randomString(){
        int len = 20;
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    @PostMapping("/setUserPassword")
    public ResponseEntity<Boolean> setUserPassword(@RequestParam("username") String username, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword){
        User user = userService.getUser(username);
        if(user != null){
            String str = new BCryptPasswordEncoder().encode(oldPassword + user.getPassword_salt());
            if(new BCryptPasswordEncoder().matches(oldPassword + user.getPassword_salt(), user.getPassword())){
                user.setPassword(new BCryptPasswordEncoder().encode(newPassword + user.getPassword_salt()));
                userService.setUserPassword(user);
                return ResponseEntity.ok(true);
            }else {
                return ResponseEntity.ok(false);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
