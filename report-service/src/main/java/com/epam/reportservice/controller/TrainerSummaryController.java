package com.epam.reportservice.controller;

import com.epam.reportservice.dto.TrainingRequestDTO;
import com.epam.reportservice.dto.TrainingResponseDTO;
import com.epam.reportservice.feign.CustomErrorDecoder;
import com.epam.reportservice.feign.MainMicroservice;
import com.epam.reportservice.service.ReportService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TrainerSummaryController {

    private final ReportService reportService;
    private final MainMicroservice mainMicroservice;
    private final HttpServletRequest request;

    @Autowired
    public TrainerSummaryController(ReportService reportService, MainMicroservice mainMicroservice, HttpServletRequest request) {
        this.reportService = reportService;
        this.mainMicroservice = mainMicroservice;
        this.request = request;
    }

    @PostMapping("/trainings/records")
    public ResponseEntity<?> recordTraining(@RequestBody TrainingRequestDTO dto) {
        if(mainMicroservice.validate(request.getHeader(HttpHeaders.AUTHORIZATION))) {
            reportService.saveTrainingRequest(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            throw new CustomErrorDecoder.ForbiddenException("Access is forbidden");
        }
    }

    @GetMapping("trainings/records/{username}")
    public ResponseEntity<?> getSummary(@PathVariable String username) {
        if(mainMicroservice.validate(request.getHeader(HttpHeaders.AUTHORIZATION))) {
            return new ResponseEntity<>(reportService.getTrainingsReport(username), HttpStatus.OK);
        }
        else {
            throw new CustomErrorDecoder.ForbiddenException("Access is forbidden");
        }
    }


}
