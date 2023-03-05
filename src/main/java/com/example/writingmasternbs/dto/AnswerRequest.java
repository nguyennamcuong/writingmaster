package com.example.writingmasternbs.dto;


import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AnswerRequest {
     String student_id;
     String topic_id;
     String content;
     LocalDateTime submit_time;
     String version;

     public String getStudent_id() {
          return student_id;
     }

     public String getTopic_id() {
          return topic_id;
     }

     public String getContent() {
          return content;
     }

     public LocalDateTime getSubmit_time() {
          return submit_time;
     }

     public String getVersion() {
          return version;
     }

     public void setStudent_id(String student_id) {
          this.student_id = student_id;
     }

     public void setTopic_id(String topic_id) {
          this.topic_id = topic_id;
     }

     public void setSubmit_time(LocalDateTime submit_time) {
          LocalDateTime localDateTime = java.time.LocalDateTime.now();
          this.submit_time = localDateTime;

     }

     public void setVersion(String version) {
          this.version = version;
     }

     public void setContent1(String content1) {
          String[] words = content1.split("\\s+"); // Tokenization
          String string = String.valueOf(words.length); // Counting the words and converting into string
          this.content = string;
     }


     public void setContent(String content) {
          this.content = content;
     }
}
