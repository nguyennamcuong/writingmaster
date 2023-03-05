package com.example.writingmasternbs.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Table(name = "tb_answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "student_id")
    private String student_id;
    @Column(name = "topic_id")
    private String topic_id;
    @Column(name = "content", length = 2000)
    private String content;
    @Column(name = "submit_time")
    private LocalDateTime submit_time;
    @Column(name = "version")
    private String version;

}
