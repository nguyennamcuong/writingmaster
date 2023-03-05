package com.example.writingmasternbs;

import com.example.writingmasternbs.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class WritingMasterNbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WritingMasterNbsApplication.class, args);
//        System.out.println("time");
//        AnswerService.timeDate();
//
//        String test;
//        test=AnswerService.wordCount("Nguyen Nam Cuong");
//        System.out.println("So luong tu trong chuoi tren la :  "+test);
//        System.out.println(java.time.LocalDateTime.now());


    }

}
