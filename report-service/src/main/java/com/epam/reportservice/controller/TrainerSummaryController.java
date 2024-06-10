package com.epam.reportservice.controller;

import com.epam.reportservice.dto.TrainingRequestDTO;
import com.epam.reportservice.listener.SQSListener;
import com.epam.reportservice.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TrainerSummaryController {

    private final ReportService reportService;
    private final SQSListener sqsListener;
    private static final Logger LOGGER = LogManager.getLogger(TrainerSummaryController.class);

    @Autowired
    public TrainerSummaryController(ReportService reportService, SQSListener sqsListener) {
        this.reportService = reportService;

        this.sqsListener = sqsListener;
    }

    @PostMapping("/trainings/records")
    public ResponseEntity<?> recordTraining(@RequestBody TrainingRequestDTO dto) {
//        if(mainMicroservice.validate(request.getHeader(HttpHeaders.AUTHORIZATION))) {
//            reportService.saveTrainingRequest(dto);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        else {
//            throw new CustomErrorDecoder.ForbiddenException("Access is forbidden");
//        }
        reportService.saveTrainingRequest(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("trainings/records/{username}")
    public ResponseEntity<?> getSummary(@PathVariable String username) {
//        if(mainMicroservice.validate(request.getHeader(HttpHeaders.AUTHORIZATION))) {
//            return new ResponseEntity<>(reportService.getTrainingsReport(username), HttpStatus.OK);
//        }
//        else {
//            throw new CustomErrorDecoder.ForbiddenException("Access is forbidden");
//        }
        return new ResponseEntity<>(reportService.getTrainingsReport(username), HttpStatus.OK);
    }

    @GetMapping("/asd")
    private void asd() throws JsonProcessingException {
        sqsListener.receiveMessage();
    }


//    @RabbitListener(queues = QUEUE_NAME)
//    public void listen(Message message) {
//        String authHeader = message.getMessageProperties().getHeader("Authorization");
//        String transactionId = message.getMessageProperties().getHeader("transactionId");
//        MDC.put("transactionId", message.getMessageProperties().getHeader("transactionId").toString());
//        TrainingRequestDTO requestDTO;
//
//        if(mainMicroservice.validate(authHeader)) {
//            try {
//                requestDTO = objectMapper.readValue(message.getBody(), TrainingRequestDTO.class);
//                reportService.saveTrainingRequest(requestDTO);
//            } catch (Exception e) {
//                LOGGER.error("Error deserializing message payload: {}", e.getMessage());
//                return;
//            }
//        } else {
//            LOGGER.error("Access is forbidden");
//        }
//    }


}
