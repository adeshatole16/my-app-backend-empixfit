package com.sport.platform.entity;

import jakarta.persistence.*;

@Entity
public class ExercisePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;     // B1 / B2 / B3
    private String title;     // Exercise name
    private String description; // Full plan

    // Getters & Setters

    public Long getId() { return id; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}