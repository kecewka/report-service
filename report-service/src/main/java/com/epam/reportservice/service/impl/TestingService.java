//package com.epam.reportservice.service.impl;
//
//import com.amazonaws.services.sqs.AmazonSQS;
//import com.amazonaws.services.sqs.model.Message;
//import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.sql.SQLOutput;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class TestingService {
//
//    private final AmazonSQS amazonSQS;
//    @Value("${aws.queue.url}")
//    private String queueUrl;
//
//    public TestingService(AmazonSQS amazonSQS) {
//        this.amazonSQS = amazonSQS;
//    }
//
//    @SqsListener("GymAppQueue")
//    public List<Message> receiveMessage() {
//        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest();
//        receiveMessageRequest.setQueueUrl(queueUrl);
//        List<Message> receivedMessages =  amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
//        System.out.println(receivedMessages);
//        return receivedMessages;
//    }
//
//
//}
