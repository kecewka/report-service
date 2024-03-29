package com.epam.reportservice.controller;

import com.epam.reportservice.dto.TrainingRequestDTO;
import com.epam.reportservice.feign.CustomErrorDecoder;
import com.epam.reportservice.feign.MainMicroservice;
import com.epam.reportservice.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpHeaders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    static final String QUEUE_NAME = "reportQueue";
    private final ObjectMapper objectMapper;
    private static final Logger LOGGER = LogManager.getLogger(TrainerSummaryController.class);

    @Autowired
    public TrainerSummaryController(ReportService reportService, MainMicroservice mainMicroservice, HttpServletRequest request, ObjectMapper objectMapper) {
        this.reportService = reportService;
        this.mainMicroservice = mainMicroservice;
        this.request = request;
        this.objectMapper = objectMapper;
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

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(Message message) {
        String authHeader = message.getMessageProperties().getHeader("Authorization");
        String transactionId = message.getMessageProperties().getHeader("transactionId");
        MDC.put("transactionId", message.getMessageProperties().getHeader("transactionId").toString());
        TrainingRequestDTO requestDTO;

        if(mainMicroservice.validate(authHeader)) {
            try {
                requestDTO = objectMapper.readValue(message.getBody(), TrainingRequestDTO.class);
                reportService.saveTrainingRequest(requestDTO);
            } catch (Exception e) {
                LOGGER.error("Error deserializing message payload: {}", e.getMessage());
                return;
            }
        } else {
            LOGGER.error("Access is forbidden");
        }
    }


}
