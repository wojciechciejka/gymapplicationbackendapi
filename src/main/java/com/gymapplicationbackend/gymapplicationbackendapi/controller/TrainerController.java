package com.gymapplicationbackend.gymapplicationbackendapi.controller;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Trainer;
import com.gymapplicationbackend.gymapplicationbackendapi.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/getTrainer")
    public ResponseEntity<Trainer> getTrainer(@RequestParam("username") String username) {
        Trainer trainer;
        trainer = trainingService.getTrainer(username);
        return ResponseEntity.ok(trainer);
    }

    @PostMapping(value = "/setTrainer")
    public ResponseEntity<String> setTrainer(@RequestBody Trainer trainer) {
        boolean result = trainingService.setTrainer(trainer);
        if(result){
            return ResponseEntity.ok("User Created Successfully !!");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
