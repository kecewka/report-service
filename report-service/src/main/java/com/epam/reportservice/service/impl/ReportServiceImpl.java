package com.epam.reportservice.service.impl;

import com.epam.reportservice.dto.TrainingRequestDTO;
import com.epam.reportservice.dto.TrainingResponseDTO;
import com.epam.reportservice.entity.ActionType;
import com.epam.reportservice.entity.TrainerSummary;
import com.epam.reportservice.entity.TrainingRequest;
import com.epam.reportservice.mapper.TrainingRequestMapper;
import com.epam.reportservice.mapper.TrainingSummaryMapper;
import com.epam.reportservice.repository.TrainerSummaryRepository;
import com.epam.reportservice.repository.TrainingsRepository;
import com.epam.reportservice.service.ReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ReportServiceImpl implements ReportService {

    private final TrainerSummaryRepository trainerSummaryRepository;
    private final TrainingsRepository trainingsRepository;
    private final TrainingRequestMapper trainingRequestMapper;
    private final TrainingSummaryMapper trainingSummaryMapper;
    private static final Logger LOGGER = LogManager.getLogger(ReportServiceImpl.class);

    @Autowired
    public ReportServiceImpl(TrainerSummaryRepository repository, TrainerSummaryRepository trainerSummaryRepository, TrainingsRepository trainingsRepository, TrainingRequestMapper trainingRequestMapper, TrainingSummaryMapper trainingSummaryMapper) {
        this.trainerSummaryRepository = trainerSummaryRepository;
        this.trainingsRepository = trainingsRepository;
        this.trainingRequestMapper = trainingRequestMapper;
        this.trainingSummaryMapper = trainingSummaryMapper;
    }

    @Override
    public void saveTrainingRequest(TrainingRequestDTO dto) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Saving the Training", transactionId);
        TrainingRequest trainingRequest = trainingRequestMapper.toEntity(dto);
        trainingsRepository.save(trainingRequest);
        updateSummary(dto);
        LOGGER.info("[Transaction ID: {}] Training Saved", transactionId);

    }

    @Override
    public TrainingResponseDTO getTrainingsReport(String username) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Getting the report for trainer with username: {}", transactionId, username);
        TrainerSummary trainerSummary = trainerSummaryRepository.findByUsername(username);

        return trainingSummaryMapper.toDTO(trainerSummary);
    }

    public void updateSummary(TrainingRequestDTO dto) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Updating the summary for trainer with username: {}", transactionId, dto.getUsername());
        TrainerSummary summary = trainerSummaryRepository.findByUsername(dto.getUsername());
        if (summary == null) {
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

        trainerSummaryRepository.save(summary);
        LOGGER.info("[Transaction ID: {}] Summary for trainer {} has been updated", transactionId, dto.getUsername());
    }

    @Override
    public long calculateDuration(TrainingRequestDTO dto) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Calculating Durations", transactionId);
        return dto.getActionType() == ActionType.ADD ? dto.getTrainingDuration() : -dto.getTrainingDuration();
    }
}
