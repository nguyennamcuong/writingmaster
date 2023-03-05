package com.example.writingmasternbs.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "tb_group")
@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String group_id;
    private String group_name;
    private String group_pass;
    private String teacher_id;
    private String note;
    private LocalDateTime duration_date;
    private Date begin_date;
    private Date expiry_date;

    public Group(String group_name, String group_pass, String teacher_id, String note, LocalDateTime duration_date, Date begin_date, Date expiry_date) {
        this.group_name = group_name;
        this.group_pass = group_pass;
        this.teacher_id = teacher_id;
        this.note = note;
        this.duration_date = duration_date;
        this.begin_date = begin_date;
        this.expiry_date = expiry_date;
    }


}
