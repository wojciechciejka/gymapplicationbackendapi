package com.gymapplicationbackend.gymapplicationbackendapi.controller;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Trainer;
import com.gymapplicationbackend.gymapplicationbackendapi.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrainerController {
    @Autowired
    private TrainerService trainingService;

    @GetMapping(value = "/getAllTrainers")
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        List<Trainer> trainers;
        trainers = trainingService.getAllTrainers();
        return ResponseEntity.ok(trainers);
    }
}
