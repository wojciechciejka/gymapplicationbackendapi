package com.gymapplicationbackend.gymapplicationbackendapi.controller;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Hello;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class HelloController {

    @PostMapping("/hello")
    public ResponseEntity<Hello> hello() {
        Hello hello = new Hello("Test name", "Test title", "Test Description");
        ResponseEntity res = new ResponseEntity<Hello>(hello, HttpStatus.OK);
        return res;
    }
}
