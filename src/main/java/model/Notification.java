package model;

import java.time.LocalDateTime;

public class Notification {
    private Long id;
    private Long userId;
    private String message;
    private LocalDateTime createdAt;


    public Notification(Long userId, String message, LocalDateTime createdAt) {
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Notification(Long userId, String message) {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
