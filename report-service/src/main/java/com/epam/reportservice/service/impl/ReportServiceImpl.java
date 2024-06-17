package com.epam.reportservice.service.impl;

import com.epam.reportservice.dto.TrainingRequestDTO;
import com.epam.reportservice.dto.TrainingResponseDTO;
import com.epam.reportservice.entity.ActionType;
import com.epam.reportservice.entity.TrainerSummary;
import com.epam.reportservice.entity.TrainingRequest;
import com.epam.reportservice.mapper.TrainingRequestMapper;
import com.epam.reportservice.mapper.TrainingSummaryMapper;
import com.epam.reportservice.service.ReportService;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.HashMap;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    private final TrainingRequestMapper trainingRequestMapper;
    private final TrainingSummaryMapper trainingSummaryMapper;
    private final DynamoDbTemplate dynamoDbTemplate;
    private static final Logger LOGGER = LogManager.getLogger(ReportServiceImpl.class);

    @Autowired
    public ReportServiceImpl(TrainingRequestMapper trainingRequestMapper, TrainingSummaryMapper trainingSummaryMapper, DynamoDbTemplate dynamoDbTemplate) {

        this.trainingRequestMapper = trainingRequestMapper;
        this.trainingSummaryMapper = trainingSummaryMapper;
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Override
    public void saveTrainingRequest(TrainingRequestDTO dto) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Saving the Training", transactionId);
        TrainingRequest trainingRequest = trainingRequestMapper.toEntity(dto);
        updateSummary(dto);
        LOGGER.info("[Transaction ID: {}] Training Saved", transactionId);

    }

    @Override
    public TrainingResponseDTO getTrainingsReport(String username) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Getting the report for trainer with username: {}", transactionId, username);
        //TrainerSummary trainerSummary = trainerSummaryRepository.findByUsername(username);
        Key key = Key.builder().partitionValue(username).build();
        QueryConditional conditional = QueryConditional.keyEqualTo(key);
        PageIterable<TrainerSummary> trainerSummaryIterable = dynamoDbTemplate.query(QueryEnhancedRequest.builder().queryConditional(conditional).build(), TrainerSummary.class);
//        TrainerSummary trainerSummary = dynamoDbTemplate.load(Key.builder()
//                .partitionValue(username)
//                .sortValue("active")
//                .build(), TrainerSummary.class);

        Optional<TrainerSummary> optional = trainerSummaryIterable.items().stream().findFirst();

        return trainingSummaryMapper.toDTO(optional.orElseThrow());
    }

    public void updateSummary(TrainingRequestDTO dto) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Updating the summary for trainer with username: {}", transactionId, dto.getUsername());
//        TrainerSummary summary = dynamoDbTemplate.load(Key.builder()
//                .partitionValue(dto.getUsername())
//                .build(), TrainerSummary.class);

        Key key = Key.builder().partitionValue(dto.getUsername()).build();
        QueryConditional conditional = QueryConditional.keyEqualTo(key);
        PageIterable<TrainerSummary> trainerSummaryIterable = dynamoDbTemplate.query(QueryEnhancedRequest.builder().queryConditional(conditional).build(), TrainerSummary.class);
        Optional<TrainerSummary> optional = trainerSummaryIterable.items().stream().findFirst();
        TrainerSummary summary = null;
        if(optional.isPresent()){
            summary = optional.get();
        }


        if (optional.isEmpty()) {
            summary = trainingSummaryMapper.requestToSummary(dto);
            summary.setSummary(new HashMap<>());
        }

        int year = dto.getTrainingDate().getYear();
        int month = dto.getTrainingDate().getMonthValue();

        summary.getSummary().putIfAbsent(year, new HashMap<>());
//        Map<Integer, Long> yearSummary = summary.getSummary().get(year);
//        for (int i = 1; i <= 12; i++) {
//            yearSummary.putIfAbsent(i, 0L);
//        }

        summary.getSummary().get(year).merge(month, calculateDuration(dto), Long::sum);

//        trainerSummaryRepository.save(summary);
        dynamoDbTemplate.update(summary);
        LOGGER.info("[Transaction ID: {}] Summary for trainer {} has been updated", transactionId, dto.getUsername());
    }

    @Override
    public long calculateDuration(TrainingRequestDTO dto) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Calculating Durations", transactionId);
        return dto.getActionType() == ActionType.ADD ? dto.getTrainingDuration() : -dto.getTrainingDuration();
    }
}