package com.epam.reportservice.mapper;

import com.epam.reportservice.dto.TrainingResponseDTO;
import com.epam.reportservice.entity.TrainerSummary;
import org.mapstruct.Mapper;

@Mapper
public interface TrainingSummaryMapper {
    TrainingResponseDTO toDTO(TrainerSummary entity);
}
