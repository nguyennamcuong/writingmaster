package com.example.writingmasternbs.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedBackRequest {
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
