package com.example.writingmasternbs.controller;

import com.example.writingmasternbs.dto.DataDto;
import com.example.writingmasternbs.dto.GroupDto;
import com.example.writingmasternbs.dto.MemberDto;
import com.example.writingmasternbs.dto.UserDetailDto;
import com.example.writingmasternbs.repository.GroupRepository;
import com.example.writingmasternbs.repository.ImageRepository;
import com.example.writingmasternbs.repository.MemberRepository;
import com.example.writingmasternbs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;


    @PostMapping("/imageUpload")
    public String imageUpload(@RequestParam("pic1") MultipartFile img, Model model) throws IOException {
        System.out.println(img.getOriginalFilename());
//        System.out.println("alo");
        String saveFile = new ClassPathResource("static/images").getFile().toString();
        System.out.println(saveFile);
        System.out.println(saveFile);
        byte[] bytes = img.getBytes();
        InputStream initialStream = img.getInputStream();
        byte[] buffer = new byte[initialStream.available()];
        initialStream.read(buffer);
        String folder = saveFile + "\\" + img.getOriginalFilename();
        File targetFile = new File(folder);
        try (OutputStream outStream = new FileOutputStream(targetFile)) {
            outStream.write(buffer);
        }
        ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(img.getOriginalFilename()).toUriString());
        UserDetailDto userDTO = new UserDetailDto();
        for (int y = 0; y < userRepository.findAll().size(); y++) {
            if (userRepository.findAll().get(y).getUserID().equals(DataDto.user_id)) {
                userDTO.setEmail(userRepository.findAll().get(y).getEmail());
                userDTO.setFirstname(userRepository.findAll().get(y).getFirstname());
                userDTO.setLastname(userRepository.findAll().get(y).getLastname());
                userDTO.setMclass(userRepository.findAll().get(y).getTarget());
                int roleid = Integer.parseInt(userRepository.findAll().get(y).getRoleID().toString());
                String rolename = "";
                switch (roleid) {
                    case 1:
                        rolename = "Giao vien tu do";
                        break;
                    case 2:
                        rolename = "Sinh vien tu do";
                        break;
                    case 3:
                        rolename = "Giao vien noi bo";
                        break;
                }
                userDTO.setRole(rolename);
                userDTO.setTarget(userRepository.findAll().get(y).getTarget());
                userDTO.setUserid(userRepository.findAll().get(y).getUserID().toString());
                userDTO.setPassword(userRepository.findAll().get(y).getPassword());
                userDTO.setSdt(userRepository.findAll().get(y).getPhonenumber());
                userDTO.setBirthdate(userRepository.findAll().get(y).getBirthdate().toString().substring(0, 10));
            }


        }

        model.addAttribute("page",userDTO);
        model.addAttribute("userid",userDTO.getUserid());
        model.addAttribute("image",img.getOriginalFilename());
        model.addAttribute("username",DataDto.user_name);
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        DataDto.imagepicture=img.getOriginalFilename();
        return "setting";
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
