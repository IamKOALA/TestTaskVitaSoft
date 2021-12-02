package com.company.VitaSoft.tasks.impl.entity;

import com.company.VitaSoft.tasks.impl.enums.ETaskStatus;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long user_id;

    @Enumerated(EnumType.STRING)
    private ETaskStatus status;

    private String message;

    public Task() {

    }

    public Task(Long id, Long user_id, ETaskStatus status, String message) {
        this.id = id;
        this.user_id = user_id;
        this.status = status;
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setStatus(ETaskStatus status) {
        this.status = status;
    }

    public ETaskStatus getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
