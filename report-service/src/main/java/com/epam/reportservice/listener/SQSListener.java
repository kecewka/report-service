package com.epam.reportservice.listener;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.epam.reportservice.dto.TrainingRequestDTO;
import com.epam.reportservice.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SQSListener {

    private final AmazonSQSAsync amazonSQS;
    private final ObjectMapper objectMapper;
    private final ReportService reportService;

    @Value("${aws.queue.url}")
    private String queueUrl;

    public SQSListener(AmazonSQSAsync amazonSQS, ObjectMapper objectMapper, ReportService reportService) {
        this.amazonSQS = amazonSQS;
        this.objectMapper = objectMapper;
        this.reportService = reportService;
    }

    @SqsListener("GymAppQueue")
    public void receiveMessage() throws JsonProcessingException {
        String transactionId = "";
        TrainingRequestDTO requestDTO;
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest();
        receiveMessageRequest.setQueueUrl(queueUrl);
        //receiveMessageRequest.withWaitTimeSeconds(3);
        //receiveMessageRequest.withMaxNumberOfMessages(1);

        List<Message> receivedMessages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
        System.out.println(receivedMessages.size());
        System.out.println(receivedMessages);
        for (Message m : receivedMessages) {
            System.out.println(m.getBody());
            System.out.println("=======================================================");
            System.out.println(m.getAttributes());
            transactionId = m.getAttributes().get("transactionId");
            System.out.println(transactionId);
            MDC.put("transactionId", m.getAttributes().get("transactionId"));
            requestDTO = objectMapper.readValue(m.getBody(), TrainingRequestDTO.class);
            reportService.saveTrainingRequest(requestDTO);
            deleteMessage(m);
        }
    }

    private void deleteMessage(Message message) {
        String receiptHandle = message.getReceiptHandle();
        amazonSQS.deleteMessage(queueUrl, receiptHandle);
    }
}
