package com.epam.reportservice.entity;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

//@Document(collection = "trainer_trainings")
public class TrainingRequest {
    //@Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private LocalDate trainingDate;
    private Long trainingDuration;
    private ActionType actionType;

    public TrainingRequest() {
    }

    public TrainingRequest(String username, String firstName, String lastName, boolean isActive, LocalDate trainingDate, Long trainingDuration, ActionType actionType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
        this.actionType = actionType;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Long getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(Long trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingRequest that = (TrainingRequest) o;
        return isActive == that.isActive && Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(trainingDate, that.trainingDate) && Objects.equals(trainingDuration, that.trainingDuration) && actionType == that.actionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, isActive, trainingDate, trainingDuration, actionType);
    }

    @Override
    public String toString() {
        return "TrainingRequest{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", trainingDate=" + trainingDate +
                ", trainingDuration=" + trainingDuration +
                ", actionType=" + actionType +
                '}';
    }
}
