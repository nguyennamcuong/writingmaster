package com.example.writingmasternbs.service;

import com.example.writingmasternbs.dto.AnswerResponse;
import com.example.writingmasternbs.dto.AnswerRequest;
import com.example.writingmasternbs.model.Answer;
import com.example.writingmasternbs.repository.AnswerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepo answerRepo;

    public List<AnswerResponse> getAnswer(List<AnswerResponse> responseList){
        List<Answer> list = answerRepo.findAll();
        for (int i = 0; i < list.size(); i++){
            responseList.add(new AnswerResponse(answerRepo.findAll().get(i).getId(),answerRepo.findAll().get(i).getStudent_id(),answerRepo.findAll().get(i).getTopic_id(),answerRepo.findAll().get(i).getContent(),answerRepo.findAll().get(i).getSubmit_time(),answerRepo.findAll().get(i).getVersion()));
        }
        return responseList;
    }

    public AnswerResponse getContentById(String id){
        AnswerResponse answer = new AnswerResponse();
        for (int i = 0; i < answerRepo.findAll().size(); i++){
            if (answerRepo.findAll().get(i).getId().equals(id)){
                answer.setContent(answerRepo.findAll().get(i).getContent());
            }

        }
        return answer;
    }

    public  void createAnswer(AnswerRequest answerRequest){
        Answer answer = Answer.builder()
                .student_id(answerRequest.getStudent_id())
                .topic_id(answerRequest.getTopic_id())
                .content(answerRequest.getContent())
                .submit_time(answerRequest.getSubmit_time())
                .version(answerRequest.getVersion())
                .build();

        answerRepo.save(answer);

        log.info("Answer {} is saved" , answer.getId());
    }

    public List<AnswerResponse> getAllAnswers() {

        List<Answer> answers = answerRepo.findAll();
         return answers.stream().map(this::mapToAnswerResponse).toList();
    }

    private AnswerResponse mapToAnswerResponse(Answer answer) {
        return AnswerResponse.builder()
                .id(answer.getId())
                .student_id(answer.getStudent_id())
                .topic_id(answer.getTopic_id())
                .content(answer.getContent())
                .submit_time(answer.getSubmit_time())
                .version(answer.getVersion())
                .build();
    }




}
