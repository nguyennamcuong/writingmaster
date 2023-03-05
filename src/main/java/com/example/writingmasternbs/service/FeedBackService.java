package com.example.writingmasternbs.service;

import com.example.writingmasternbs.dto.AnswerRequest;
import com.example.writingmasternbs.dto.AnswerResponse;
import com.example.writingmasternbs.dto.FeedBackRequest;
import com.example.writingmasternbs.model.Answer;
import com.example.writingmasternbs.model.FeedBack;
import com.example.writingmasternbs.repository.FeedBackRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class FeedBackService {
    private final FeedBackRepo feedBackRepo;

    public  void createFeedBack(FeedBackRequest feedBackRequest){
        FeedBack feedBack = FeedBack.builder()
                .answer_id(feedBackRequest.getAnswer_id())
                .teacher_id(feedBackRequest.getTeacher_id())
                .sent_date(feedBackRequest.getSent_date())
                .content(feedBackRequest.getContent())
                .score(feedBackRequest.getScore())
                .build();

        feedBackRepo.save(feedBack);
        log.info("Answer {} is saved" , feedBack.getId());
    }

//    public List<AnswerResponse> getAllAnswers() {
//        List<Answer> answers = feedBackRepo.findAll();
//        return answers.stream().map(this::mapToAnswerResponse).toList();
//    }

//    private AnswerResponse mapToAnswerResponse(Answer answer) {
//        return AnswerResponse.builder()
//                .id(answer.getId())
//                .student_id(answer.getStudent_id())
//                .topic_id(answer.getTopic_id())
//                .content(answer.getContent())
//                .submit_time(answer.getSubmit_time())
//                .version(answer.getVersion())
//                .build();
//    }
}
