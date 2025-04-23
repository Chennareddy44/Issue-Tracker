package com.IssueTracker.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Issue {
    private int id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int assigneeId;
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Issue() {
    }

    public Issue(String title, String description, String status, String priority, int assigneeId) {
        this.title = title;
        this.description = description;
        this.status = status; // Use provided status
        this.priority = priority;
        this.assigneeId = assigneeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    @Override
    public String toString() {
        return "Issue [id=" + id + ", title=" + title + ", description=" + description +
                ", status=" + status + ", priority=" + priority +
                ", createdAt=" + createdAt + ", updatedAt=" + updatedAt +
                ", assigneeId=" + assigneeId + "]";
    }
}