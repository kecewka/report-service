package com.epam.reportservice.dto;

import java.util.Map;

public class TrainerSummaryDTO {
    private String username;
    private String firstName;
    private String lastName;
    private boolean status;
    private Map<Integer, Map<Integer, Integer>> years;

    public TrainerSummaryDTO(){}

    public TrainerSummaryDTO(String username, String firstName, String lastName, boolean status, Map<Integer, Map<Integer, Integer>> years) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.years = years;
    }
}
