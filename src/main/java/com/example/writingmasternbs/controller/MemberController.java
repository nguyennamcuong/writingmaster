package com.example.writingmasternbs.controller;

import com.example.writingmasternbs.dto.DataDto;
import com.example.writingmasternbs.dto.GroupDto;
import com.example.writingmasternbs.dto.MemberDto;
import com.example.writingmasternbs.repository.GroupRepository;
import com.example.writingmasternbs.repository.ImageRepository;
import com.example.writingmasternbs.repository.MemberRepository;
import com.example.writingmasternbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Controller
public class MemberController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ImageRepository imageRepository;
    @RequestMapping("deletemember")
    public String deletemember(Model model, @RequestParam("id")String id){
        List<GroupDto> list = new ArrayList<>();
//        list.add(new UserEntity("a","b"));
//        list.add(new UserEntity("c","b"));
        System.out.println(id);
        for(int i=0;i<memberRepository.findAll().size();i++)
        {
            if(memberRepository.findAll().get(i).getMember_id().equals(id))
            {
                memberRepository.deleteById((String) id);
            }
        }
        list = getnewlist(list);
        model.addAttribute("loadUser",list);
        GroupDto groupDTO = new GroupDto();
        model.addAttribute("groupdto",groupDTO);
        model.addAttribute("username",DataDto.user_name);
        String hinhanh = "";
        boolean checked = false;
        for (int i=0;i<imageRepository.findAll().size();i++)
        {
            if(imageRepository.findAll().get(i).getUser_id().equals(DataDto.user_id))
            {
                hinhanh = imageRepository.findAll().get(i).getImage_name();
                checked=true;
            }
        }
        if(checked==false)
        {
            model.addAttribute("image","nothing.png");

        }
        else{
            model.addAttribute("image",hinhanh);

        }
        return "manageGroup";
    }
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

}
