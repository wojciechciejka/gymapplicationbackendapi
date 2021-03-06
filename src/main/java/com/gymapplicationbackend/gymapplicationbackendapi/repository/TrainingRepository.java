package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Training;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository {
    List<Training> getUserTrainings(String username);

    boolean addUserTraining(Training training);

    boolean deleteUserTraining(String playerUsername, String trainerUsername, long date);
}
