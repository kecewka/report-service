package com.epam.reportservice.entity;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.util.Map;

@DynamoDbBean
public class TrainerSummary {

    private String username;

    private String firstName;

    private String lastName;

    private String status;

    private Map<Integer, Map<Integer, Long>> summary;

    public TrainerSummary() {
    }

    public TrainerSummary(String username, String firstName, String lastName, String status, Map<Integer, Map<Integer, Long>> summary) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.summary = summary;
    }


    @DynamoDbPartitionKey
    @DynamoDbAttribute("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDbAttribute("firstName")
    @DynamoDbSecondaryPartitionKey(indexNames = {"TrainerName-Index"})
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @DynamoDbAttribute("lastName")
    @DynamoDbSecondarySortKey(indexNames = {"TrainerName-Index"})
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @DynamoDbAttribute("summary")
    public Map<Integer, Map<Integer, Long>> getSummary() {
        return summary;
    }

    public void setSummary(Map<Integer, Map<Integer, Long>> summary) {
        this.summary = summary;
    }
}