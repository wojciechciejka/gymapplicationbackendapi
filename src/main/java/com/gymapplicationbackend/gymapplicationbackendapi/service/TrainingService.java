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

    public boolean addUserTraining(String username, Training training) {
        return trainingRepository.addUserTraining(username, training);
    }

    public boolean deleteUserTraining(String username, long date) {
        return trainingRepository.deleteUserTraining(username, date);
    }
}