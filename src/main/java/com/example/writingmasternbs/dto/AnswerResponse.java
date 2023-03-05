package com.example.writingmasternbs.dto;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnswerResponse {
    private String id;
    private String student_id;
    private String topic_id;
    private String content;
    private LocalDateTime submit_time;
    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(LocalDateTime submit_time) {
        this.submit_time = submit_time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
