package com.example.writingmasternbs.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
@Setter
public class TaskDTO {

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

    private String id;

    public TaskDTO(String task_type, String task_about, LocalDate date_create, String teacher_id, String task_from, String task_to, LocalDate date_begin, LocalDate date_end, String task_content, String group_id, String id) {
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
        this.id = id;
    }

    public TaskDTO() {
    }
}
