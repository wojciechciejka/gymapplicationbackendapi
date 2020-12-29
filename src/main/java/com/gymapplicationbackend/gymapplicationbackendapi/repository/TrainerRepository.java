package com.gymapplicationbackend.gymapplicationbackendapi.repository;

import com.gymapplicationbackend.gymapplicationbackendapi.model.Trainer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository {
    List<Trainer> getAllTrainers();
}
