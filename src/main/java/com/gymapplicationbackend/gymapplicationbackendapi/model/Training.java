package com.gymapplicationbackend.gymapplicationbackendapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Training implements Serializable {
    private long date;
    private int durationInMinutes;
    private String description;
    private String address;
    private String trainerFullName;
    private String typeofTraining;
}
