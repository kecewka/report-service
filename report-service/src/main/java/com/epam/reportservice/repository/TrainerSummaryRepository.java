package com.epam.reportservice.repository;

import com.epam.reportservice.entity.TrainerSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerSummaryRepository extends MongoRepository<TrainerSummary, String> {

}
