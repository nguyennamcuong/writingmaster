package com.example.writingmasternbs.repository;

import com.example.writingmasternbs.model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FeedBackRepo extends JpaRepository<FeedBack,String> {

}
