package com.gymapplicationbackend.gymapplicationbackendapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hello {
    private String name;
    private String title;
    private String description;

    public Hello(String name, String title, String description) {
        this.name = name;
        this.title = title;
        this.description = description;
    }
}
