package com.epam.reportservice.dto;

import java.util.Map;

public class TrainingResponseDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String status;
    private Map<Integer, Map<Integer, Long>> summary;

    public TrainingResponseDTO(){}

    public TrainingResponseDTO(String username, String firstName, String lastName, String status, Map<Integer, Map<Integer, Long>> summary) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.summary = summary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<Integer, Map<Integer, Long>> getSummary() {
        return summary;
    }

    public void setSummary(Map<Integer, Map<Integer, Long>> summary) {
        this.summary = summary;
    }
}
