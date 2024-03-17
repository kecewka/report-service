package com.epam.reportservice.mapper;

import com.epam.reportservice.dto.TrainingRequestDTO;
import com.epam.reportservice.dto.TrainingResponseDTO;
import com.epam.reportservice.entity.TrainerSummary;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TrainingSummaryMapper {
    TrainingResponseDTO toDTO(TrainerSummary entity);
    @Mapping(source = "username", target = "username")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "active", target = "status")
    TrainerSummary requestToSummary(TrainingRequestDTO request);
}
