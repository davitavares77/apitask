package com.rev.revisao.model;

import jakarta.persistence.*;

    @Entity
@Table(name = "tasks")
public class Task {
 

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String userId;
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    @Column
    private String description;

    @Column(nullable = false)
    private Boolean completed = false;

    public Task(){}

    public Task(String title, String description){
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public Long getId() {
    return id;
    }

    public void setId(Long id) {
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

    public Boolean getCompleted() {
    return completed;
    }

    public void setCompleted(Boolean completed) {
    this.completed = completed;
    }

// Constructors, getters e setters
}

