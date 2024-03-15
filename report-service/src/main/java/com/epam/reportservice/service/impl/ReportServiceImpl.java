package com.epam.reportservice.service.impl;

import com.epam.reportservice.mapper.TrainingRequestMapper;
import com.epam.reportservice.mapper.TrainingSummaryMapper;
import com.epam.reportservice.repository.TrainerSummaryRepository;
import com.epam.reportservice.repository.TrainingsRepository;
import com.epam.reportservice.service.ReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void save() {

    }

    @Override
    public void calculate() {

    }

    @Override
    public void get() {

    }
}
