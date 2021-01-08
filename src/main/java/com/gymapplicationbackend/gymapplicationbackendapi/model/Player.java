package com.gymapplicationbackend.gymapplicationbackendapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Player implements Serializable {
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    private String emailId;
    private String description;
    private int age;
}
