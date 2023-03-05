package com.example.writingmasternbs.controller;

import com.example.writingmasternbs.dto.*;
import com.example.writingmasternbs.repository.*;
import com.example.writingmasternbs.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;
    public List<GroupDto> getnewlist(List<GroupDto> list){
        for(int i=0;i < groupRepository.findAll().size();i++)
        {
            //phanchinhsua
            if(groupRepository.findAll().get(i).getTeacher_id().equals(DataDto.user_id))
            {
                String teachername="";
                List<MemberDto> memberDTOS = new ArrayList<>();
                for(int j=0;j<userRepository.findAll().size();j++)
                {
                    if(groupRepository.findAll().get(i).getTeacher_id().equals(userRepository.findAll().get(j).getUserID())) {
                        teachername = userRepository.findAll().get(j).getFirstname() + " " + userRepository
                                .findAll().get(j).getLastname();
                    }
                }
                for(int k=0;k<memberRepository.findAll().size();k++)
                {
                    if(memberRepository.findAll().get(k).getGroup_id().equals(groupRepository.findAll().get(i).getGroup_id()))
                    {
                        for(int j=0;j<userRepository.findAll().size();j++)
                        {
                            if(userRepository.findAll().get(j).getUserID().equals(memberRepository.findAll().get(k).getLearner_id()))
                            {
                                SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd");
                                memberDTOS.add(new MemberDto(userRepository.findAll().get(j).getFirstname() + " " + userRepository
                                        .findAll().get(j).getLastname(),memberRepository.findAll().get(k).getDate_join().toString(),
                                        memberRepository.findAll().get(k).getMember_id()));
                            }
                        }
                    }
                }
                list.add(new GroupDto(groupRepository.findAll().get(i).getGroup_name(),teachername,memberDTOS,groupRepository
                        .findAll().get(i).getGroup_id(),groupRepository.findAll().get(i).getGroup_pass(),groupRepository.findAll().get(i).getNote()
                ));
            }
        }
        return list;
    }

    @PostMapping("/createAnswer")
    public String createAnswer(@ModelAttribute("answerRequest") AnswerRequest answerRequest, Model model,@RequestParam("id") String id){
        answerRequest.setTopic_id(id);
        answerService.createAnswer(answerRequest);
        List<AnswerResponse> list = new ArrayList<>();
        answerService.getAnswer(list);
        model.addAttribute( "answerResponse",list);
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "managePostStudent";
    }


    @GetMapping("/getPage")
    public String getPage(Model model){
        List<AnswerResponse> list = new ArrayList<>();
        answerService.getAnswer(list);
        model.addAttribute("answerResponse",list);
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "managePostStudent";
    }


    @RequestMapping("/getPageStudent")
    public String getPage(Model model, @RequestParam("id") String id){
        System.out.println(id);
        AnswerRequest answerRequest = new AnswerRequest();
        model.addAttribute("answerRequest", answerRequest);
        model.addAttribute("id",id);
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "pageWriting";
    }

    @GetMapping("/getPageManagePostStudent")
    public String getPageManagePostStudent(Model model){
        List<AnswerResponse> list = new ArrayList<>();
        answerService.getAnswer(list);
        model.addAttribute("answerResponse",list);
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "managePostStudent";
    }
    @RequestMapping("getAnswer")
    public String getdata(Model model,@RequestParam("id") String id)
    {
        AnswerResponse answerResponse = new AnswerResponse();
       answerResponse= answerService.getContentById(id);
        model.addAttribute("answer",answerResponse);
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "studentFeedback";
    }

    @GetMapping("/getPageManagePostTeacher")
    public String getPageManagePostTeacher(Model model){
        List<AnswerResponse> list = (List<AnswerResponse>) answerService.getAllAnswers().stream().toList();
        System.out.println(list);
        model.addAttribute("answerResponse",list);
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "managePostTeacher";
    }

    @GetMapping("/getFeedbackStudent")
    public String getStudentFeedBack(Model model){
        List<AnswerResponse> list = new ArrayList<>();
        answerService.getAnswer(list);
        model.addAttribute("answerResponse",list);
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "studentFeedback";
    }

    @GetMapping("/getAll")
    public String getAllAnswers( Model model){
        List<AnswerResponse> list = (List<AnswerResponse>) answerService.getAllAnswers().stream().toList();
        System.out.println(list);
        model.addAttribute("answerResponse",list);
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "test";
    }

}
