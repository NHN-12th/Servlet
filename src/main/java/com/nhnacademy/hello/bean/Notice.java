package com.nhnacademy.hello.bean;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    public Notice() {

    }

    private String subject;
    private String name;
    private Long counter;
    private Date createdAt;

    public Notice(String subject, String name, Long counter) {
        this.subject = subject;
        this.name = name;
        this.counter = counter;
        this.createdAt = new Date();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
