package com.gymapplicationbackend.gymapplicationbackendapi.service;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Training;
import com.gymapplicationbackend.gymapplicationbackendapi.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    public List<Training> getUserTrainings(String username) {
        return trainingRepository.getUserTrainings(username);
    }

    public boolean addUserTraining(Training training) {
        return trainingRepository.addUserTraining(training);
    }

    public boolean deleteUserTraining(String playerUsername, String trainerUsername, long date) {
        return trainingRepository.deleteUserTraining(playerUsername, trainerUsername, date);
    }
}