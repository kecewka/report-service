package com.epam.reportservice.repository;

import com.epam.reportservice.entity.TrainingRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingsRepository extends MongoRepository<TrainingRequest, String> {
}
