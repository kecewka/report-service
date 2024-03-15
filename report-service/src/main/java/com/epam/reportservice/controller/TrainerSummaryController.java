package com.epam.reportservice.controller;

import com.epam.reportservice.dto.TrainingResponseDTO;
import com.epam.reportservice.dto.TrainingRequestDTO;
import com.epam.reportservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TrainerSummaryController {

    private final ReportService reportService;

    @Autowired
    public TrainerSummaryController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/trainings/records")
    public void recordTraining(TrainingRequestDTO dto) {

    }

    @GetMapping("trainings/records/{username}")
    public TrainingResponseDTO getSummary(@PathVariable String username) {
        return null;
    }


}
