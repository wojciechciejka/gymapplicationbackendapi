package com.gymapplicationbackend.gymapplicationbackendapi.controller;

import com.gymapplicationbackend.gymapplicationbackendapi.model.NewUser;
import com.gymapplicationbackend.gymapplicationbackendapi.model.Training;
import com.gymapplicationbackend.gymapplicationbackendapi.model.User;
import com.gymapplicationbackend.gymapplicationbackendapi.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @GetMapping(value = "/getUserTrainings")
    public ResponseEntity<List<Training>> getUserTrainings(@RequestParam("username") String username) {
        List<Training> trainings;
        trainings = trainingService.getUserTrainings(username);
        return ResponseEntity.ok(trainings);
    }

    @PostMapping("/addUserTrainings")
    public ResponseEntity<String> addUserTraining(@RequestParam("username") String username, @RequestBody Training training){
        boolean result = trainingService.addUserTraining(username, training);
        if(result){
            return ResponseEntity.ok("Training added Successfully !!");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/deleteUserTrainings")
    public ResponseEntity<String> deleteUserTraining(@RequestParam("username") String username, @RequestParam("date") long date){
        boolean result = trainingService.deleteUserTraining(username, date);
        if(result){
            return ResponseEntity.ok("Training deleted Successfully !!");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}