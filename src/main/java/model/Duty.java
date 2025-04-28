package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Duty {
    private Long id;
    private User user;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;


    public Duty() {
        this.id = id;
        this.user = user;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getter для id
    public Long getId() {
        return id;
    }

    // Setter для id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter для user
    public User getUser() {
        return user;
    }

    // Setter для user
    public void setUser(User user) {
        this.user = user;
    }

    // Getter для date
    public LocalDate getDate() {
        return date;
    }

    // Setter для date
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter для startTime
    public LocalTime getStartTime() {
        return startTime;
    }

    // Setter для startTime
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    // Getter для endTime
    public LocalTime getEndTime() {
        return endTime;
    }

    // Setter для endTime
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // Getter для status
    public String getStatus() {
        return status;
    }

    // Setter для status
    public void setStatus(String status) {
        this.status = status;
    }
}
