package com.example.writingmasternbs.repository;


import com.example.writingmasternbs.model.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, String> {

}
