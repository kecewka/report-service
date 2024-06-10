package com.epam.reportservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "trainer_summaries")
@CompoundIndexes({
        @CompoundIndex(name = "firstname_lastname_index", def = "{'firstName' : 1, 'lastName' : 1}")
})
public class TrainerSummary {
    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean status;
    private Map<Integer, Map<Integer, Long>> summary;

    public TrainerSummary() {
    }

    public TrainerSummary(String id, String username, String firstName, String lastName, boolean status, Map<Integer, Map<Integer, Long>> summary) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.summary = summary;
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

    public Map<Integer, Map<Integer, Long>> getSummary() {
        return summary;
    }

    public void setSummary(Map<Integer, Map<Integer, Long>> summary) {
        this.summary = summary;
    }
}