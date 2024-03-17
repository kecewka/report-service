package com.epam.reportservice.service;

import com.epam.reportservice.dto.TrainingRequestDTO;
import com.epam.reportservice.dto.TrainingResponseDTO;

public interface ReportService {

    /**
     * Saves the training request provided by the given DTO into the database.
     *
     * @param dto The TrainingRequestDTO containing the details of the training request.
     */
    void saveTrainingRequest(TrainingRequestDTO dto);

    /**
     * Retrieves a training report for the given username, summarizing the trainings.
     *
     * @param username The username for which the training report is generated.
     * @return A TrainingResponseDTO containing the training report.
     */
    TrainingResponseDTO getTrainingsReport(String username);

    /**
     * Updates the summary based on the information provided by the TrainingRequestDTO.
     *
     * @param dto The TrainingRequestDTO containing the details of the training request.
     */
    void updateSummary(TrainingRequestDTO dto);

    /**
     * Calculates the duration based on the information provided by the TrainingRequestDTO.
     *
     * @param dto The TrainingRequestDTO containing the details of the training request.
     * @return The duration of the training in minutes.
     */
    long calculateDuration(TrainingRequestDTO dto);
}
