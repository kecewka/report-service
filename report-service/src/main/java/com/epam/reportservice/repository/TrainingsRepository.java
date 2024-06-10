package com.epam.reportservice.repository;

import com.epam.reportservice.entity.TrainingRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingsRepository extends MongoRepository<TrainingRequest, String> {
}
