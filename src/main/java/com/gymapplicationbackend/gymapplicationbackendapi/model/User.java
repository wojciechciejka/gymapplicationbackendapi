package com.gymapplicationbackend.gymapplicationbackendapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String emailId;
    private int age;
}
