package com.example.writingmasternbs.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Table(name = "tb_feedBack")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "answer_id")
    private String answer_id;
    @Column(name = "teacher_id")
    private String teacher_id;
    @Column(name = "sent_date")
    private LocalDateTime sent_date;
    @Column(name = "content")
    private String content;
    @Column(name = "score")
    private float score;
}
