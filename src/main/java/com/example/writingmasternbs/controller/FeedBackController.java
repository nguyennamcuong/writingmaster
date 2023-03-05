package com.example.writingmasternbs.controller;

import com.example.writingmasternbs.dto.FeedBackRequest;
import com.example.writingmasternbs.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class FeedBackController {

    private final FeedBackService feedBackService;

    @PostMapping("/createFeedBack")
    public String createAnswer(@ModelAttribute FeedBackRequest feedBackRequest, Model model){
        System.out.println(feedBackRequest.getContent() != null ? feedBackRequest.getContent() : "null list");
        feedBackService.createFeedBack(feedBackRequest);
        FeedBackRequest feedBackRequest1 = new FeedBackRequest();
        model.addAttribute("answerRequest", feedBackRequest1);
        return "managePostTeacher";
    }

    @RequestMapping("/getFeedbackStudent")
    public String getFeedback(Model model){
        FeedBackRequest feedBack = new FeedBackRequest();
        model.addAttribute("feedback", feedBack);
        return "studentFeedback";
    }
    @RequestMapping("/getFeedbackTeacher")
    public String getFeedbackTeacher(Model model){
        FeedBackRequest feedBack = new FeedBackRequest();
        model.addAttribute("feedback", feedBack);
        return "teacherFeedback";
    }
}
