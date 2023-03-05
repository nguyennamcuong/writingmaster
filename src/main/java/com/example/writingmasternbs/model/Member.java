package com.example.writingmasternbs.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "tb_member")
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String member_id;
    private String learner_id;
    private String group_id;
    private LocalDateTime date_join;

    public Member(String learner_id, String group_id, LocalDateTime date_join) {
        this.learner_id = learner_id;
        this.group_id = group_id;
        this.date_join = date_join;
    }
}
