package com.example.writingmasternbs.controller;

import com.example.writingmasternbs.dto.DataDto;
import com.example.writingmasternbs.dto.GroupDto;
import com.example.writingmasternbs.dto.MemberDto;
import com.example.writingmasternbs.dto.UserRegisterDto;
import com.example.writingmasternbs.model.User;
import com.example.writingmasternbs.repository.GroupRepository;
import com.example.writingmasternbs.repository.ImageRepository;
import com.example.writingmasternbs.repository.MemberRepository;
import com.example.writingmasternbs.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;

    public RegisterController(UserRepository userRepository, ImageRepository imageRepository, GroupRepository groupRepository, MemberRepository memberRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
    }


    @RequestMapping("/register")
    public String register(Model model){
        System.out.println(userRepository.findAll().toString());
        UserRegisterDto userDto = new UserRegisterDto();
//        userDto.setUsername("nam");
//        userDto.setPassword("chu");
        model.addAttribute("user",userDto);
        return "register";
    }
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String registerform(@ModelAttribute UserRegisterDto userregisterDto, Model model) {
        System.out.println(userregisterDto.getRoleid());
        boolean check = false;
        for( int i=0; i< userRepository.findAll().size();i++)
        {
            if(userregisterDto.getEmail().equals(userRepository.findAll().get(i).getEmail()))
            {
                check=true;
            }
        }
        if(check==true)
        {
            model.addAttribute("user",userregisterDto);
            model.addAttribute("error", "Email đã được sử dụng");
            return "register";
        }
        else {
            User user=new User(userregisterDto.getRoleid(),userregisterDto.getEmail() , userregisterDto.getPassword());
            userRepository.save(user);
            DataDto.user_id = userRepository.findAll().get(userRepository.findAll().size()-1).getUserID();
            List<GroupDto> list = new ArrayList<>();
//        list.add(new UserEntity("a","b"));
//        list.add(new UserEntity("c","b"));
            list= getnewlist(list);
            model.addAttribute("loadUser", list);
            if (DataDto.user_name == null) {
                model.addAttribute("username", "Emptyname");
            } else {
                model.addAttribute("username", DataDto.user_name);

            }
            String hinhanh = "";
            boolean checked = false;
            for (int i = 0; i < imageRepository.findAll().size(); i++) {
                if (imageRepository.findAll().get(i).getUser_id().equals(DataDto.user_id)) {
                    hinhanh = imageRepository.findAll().get(i).getImage_name();
                    checked = true;
                }
            }
            if (checked == false) {
                model.addAttribute("image", "nothing.png");

            } else {
                model.addAttribute("image", hinhanh);

            }
            return "manageGroup";
        }
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
