//package com.epam.reportservice.controller;
//
//import com.amazonaws.services.sqs.model.Message;
//import com.epam.reportservice.service.impl.TestingService;
//import feign.Headers;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class TestingController {
//
//    private final TestingService testingService;
//
//    public TestingController(TestingService testingService) {
//        this.testingService = testingService;
//    }
//
//
//    @GetMapping("/asd")
//    public List<Message> receiveMessage(String message, Map<String, String> headers) {
//        return testingService.receiveMessage();
//        // Process the message
//        // Add your business logic here
//    }
//}
