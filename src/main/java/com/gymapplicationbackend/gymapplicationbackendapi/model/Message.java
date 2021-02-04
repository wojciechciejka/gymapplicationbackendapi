package com.gymapplicationbackend.gymapplicationbackendapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private String content;
    private Long date;
    private String authorUsername;
}
