package com.gymapplicationbackend.gymapplicationbackendapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCorespondant implements Serializable {
    private String fullName;
    private String username;
    private String role;
    private boolean newNotification;
}
