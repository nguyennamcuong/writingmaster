package com.example.writingmasternbs.repository;


import com.example.writingmasternbs.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task,String> {
}
