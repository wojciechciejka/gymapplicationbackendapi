package com.gymapplicationbackend.gymapplicationbackendapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Trainer implements Serializable {
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    private String emailId;
    private int timeOfExperience;
    private int age;
    private String description;
}
