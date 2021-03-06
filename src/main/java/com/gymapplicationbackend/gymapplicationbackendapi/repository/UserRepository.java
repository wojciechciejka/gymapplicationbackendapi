package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    boolean addUser(User user);

    List<User> getAllUser();

    boolean setUserPassword(User user);

    User getUser(String username);

    boolean logoutUser(String username);
}
