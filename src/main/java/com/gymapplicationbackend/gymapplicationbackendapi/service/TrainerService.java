package com.gymapplicationbackend.gymapplicationbackendapi.service;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Trainer;
import com.gymapplicationbackend.gymapplicationbackendapi.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public List<Trainer> getAllTrainers() {
        return trainerRepository.getAllTrainers();
    }

    public Trainer getTrainer(String username) {
        return trainerRepository.getTrainer(username);
    }

    public boolean setTrainer(Trainer trainer) {
        return trainerRepository.setTrainer(trainer);
    }
}
