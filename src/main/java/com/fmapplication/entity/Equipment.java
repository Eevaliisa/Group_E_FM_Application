package com.fmapplication.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDate startTime;

    @Column
    private LocalDate endTime;

    @Column
    private double rate;

    @Column(name = "user_id")
    private int userId;

    @Column
    private int number;

    @Column
    private String status;

    @Column
    private String createdBy;

    @Column
    private String acceptedBy;

    public Equipment(int id, String title, String description, LocalDate startTime, LocalDate endTime, double rate, int userId) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rate = rate;
        this.userId = userId;
    }

    public Equipment(String title, String description, LocalDate startTime, LocalDate endTime, double rate, int userId) {
        super();
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rate = rate;
        this.userId = userId;
    }

    public Equipment(String title, String description, double rate, int userId) {
        this.title = title;
        this.description = description;
        this.rate = rate;
        this.userId = userId;
    }

    public Equipment(int id) {
        super();
        this.id = id;
    }

    public Equipment() {
        super();
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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", rate=" + rate +
                ", userId=" + userId +
                '}';
    }
}
