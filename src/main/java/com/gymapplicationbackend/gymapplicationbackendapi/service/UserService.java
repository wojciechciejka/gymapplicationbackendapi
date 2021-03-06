package com.gymapplicationbackend.gymapplicationbackendapi.service;

import com.gymapplicationbackend.gymapplicationbackendapi.model.User;
import com.gymapplicationbackend.gymapplicationbackendapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    public boolean saveUser(User user) {
        return UserRepository.addUser(user);
    }

    public List<User> fetchAllUser() {
        return UserRepository.getAllUser();
    }

    public boolean setUserPassword(User user) {
        return UserRepository.setUserPassword(user);
    }

    public User getUser(String username) {
        return UserRepository.getUser(username);
    }

    public boolean logoutUser(String username) {
        return UserRepository.logoutUser(username);
    }
}
