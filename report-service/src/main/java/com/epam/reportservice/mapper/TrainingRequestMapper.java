package com.epam.reportservice.mapper;

import com.epam.reportservice.dto.TrainingRequestDTO;
import com.epam.reportservice.entity.TrainingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TrainingRequestMapper {
    @Mapping(target = "id", ignore = true)
    TrainingRequest toEntity(TrainingRequestDTO dto);

    TrainingRequestDTO toDTO(TrainingRequest entity);
}
