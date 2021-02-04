package com.gymapplicationbackend.gymapplicationbackendapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class NewUser implements Serializable {
    private String firstName;
    private String lastName;
    private String username;
    private String emailId;
    private String password;
    private String role;
    private int age;
}
