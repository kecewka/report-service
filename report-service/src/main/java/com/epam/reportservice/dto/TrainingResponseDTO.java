package com.epam.reportservice.dto;

import java.util.Map;

public class TrainingResponseDTO {
    private String username;
    private String firstName;
    private String lastName;
    private boolean status;
    private Map<Integer, Map<Integer, Long>> years;

    public TrainingResponseDTO(){}

    public TrainingResponseDTO(String username, String firstName, String lastName, boolean status, Map<Integer, Map<Integer, Long>> years) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.years = years;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Map<Integer, Map<Integer, Long>> getYears() {
        return years;
    }

    public void setYears(Map<Integer, Map<Integer, Long>> years) {
        this.years = years;
    }
}
