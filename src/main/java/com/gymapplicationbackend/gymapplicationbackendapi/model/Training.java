package com.gymapplicationbackend.gymapplicationbackendapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Training implements Serializable {
    private long date;
    private int durationInMinutes;
    private String description;
    private String address;
    private String trainerUsername;
    private String playerUsername;
    private String typeofTraining;
}
