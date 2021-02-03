package com.gymapplicationbackend.gymapplicationbackendapi.config;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class SessionUser implements Serializable {

    private String username;

    private String password;

    private String role;

    private Date created;

    public boolean hasExpired() {
        if (created == null) {
            return true;
        }
        LocalDateTime localDateTime = created.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusHours(1);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()).before(new Date());
    }

    public String getRole() {
        return role;
    }
}
