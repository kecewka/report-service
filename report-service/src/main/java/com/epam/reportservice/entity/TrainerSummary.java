package com.epam.reportservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collation = "trainer_summaries")
public class TrainerSummary {
    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean status;
    private Map<Integer, Map<Integer, Integer>> years;

    public TrainerSummary(){}

    public TrainerSummary(String id, String username, String firstName, String lastName, boolean status, Map<Integer, Map<Integer, Integer>> years) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.years = years;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Map<Integer, Map<Integer, Integer>> getYears() {
        return years;
    }

    public void setYears(Map<Integer, Map<Integer, Integer>> years) {
        this.years = years;
    }
}
