package com.example.writingmasternbs.model;

import jakarta.persistence.*;
import lombok.Builder;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "tb_task")

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String task_type;
    private String task_about;
    private LocalDate date_create;
    private String teacher_id;
    private String task_from;
    private String task_to;
    private LocalDate date_begin;
    private LocalDate date_end;
    private String task_content;
    private String group_id;

    public Task() {
    }

    public Task(String id, String task_type, String task_about, LocalDate date_create, String teacher_id, String task_from, String task_to, LocalDate date_begin, LocalDate date_end, String task_content, String group_id) {
        this.id = id;
        this.task_type = task_type;
        this.task_about = task_about;
        this.date_create = date_create;
        this.teacher_id = teacher_id;
        this.task_from = task_from;
        this.task_to = task_to;
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.task_content = task_content;
        this.group_id = group_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTask_about() {
        return task_about;
    }

    public void setTask_about(String task_about) {
        this.task_about = task_about;
    }

    public LocalDate getDate_create() {
        return date_create;
    }

    public void setDate_create(LocalDate date_create) {
        this.date_create = date_create;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTask_from() {
        return task_from;
    }

    public void setTask_from(String task_from) {
        this.task_from = task_from;
    }

    public String getTask_to() {
        return task_to;
    }

    public void setTask_to(String task_to) {
        this.task_to = task_to;
    }

    public LocalDate getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(LocalDate date_begin) {
        this.date_begin = date_begin;
    }

    public LocalDate getDate_end() {
        return date_end;
    }

    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }

    public String getTask_content() {
        return task_content;
    }

    public void setTask_content(String task_content) {
        this.task_content = task_content;
    }



    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
