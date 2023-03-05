package com.example.writingmasternbs.controller;

import com.example.writingmasternbs.dto.DataDto;
import com.example.writingmasternbs.dto.GroupDto;
import com.example.writingmasternbs.dto.MemberDto;
import com.example.writingmasternbs.dto.UserDetailDto;
import com.example.writingmasternbs.model.Image;
import com.example.writingmasternbs.model.User;
import com.example.writingmasternbs.repository.GroupRepository;
import com.example.writingmasternbs.repository.ImageRepository;
import com.example.writingmasternbs.repository.MemberRepository;
import com.example.writingmasternbs.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.writingmasternbs.dto.DataDto.imagepicture;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;

    public UserController(UserRepository userRepository, ImageRepository imageRepository, GroupRepository groupRepository, MemberRepository memberRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
    }


    @GetMapping("/edituser")
    public String getgiatri(Model model){
        System.out.println(userRepository.findAll());
        UserDetailDto userDTO = new UserDetailDto();
//        List<UserDTO> userDTOS= new ArrayList<>();
        //mac dinh id=3
        String hinhanh="";
        for (int i =0;i<userRepository.findAll().size();i++){
            if (userRepository.findAll().get(i).getUserID().equals(DataDto.user_id))
            {
                try{
                    userDTO.setEmail(userRepository.findAll().get(i).getEmail());
                    userDTO.setFirstname(userRepository.findAll().get(i).getFirstname());
                    userDTO.setLastname(userRepository.findAll().get(i).getLastname());
                    userDTO.setMclass(userRepository.findAll().get(i).getTarget());
                    int roleid = Integer.parseInt(userRepository.findAll().get(i).getRoleID().toString());
                    String rolename ="";
                    switch (roleid)
                    {
                        case 1:
                            rolename="Giao vien tu do";
                            break;
                        case 2:
                            rolename="Sinh vien tu do";
                            break;
                        case 3:
                            rolename="Giao vien noi bo";
                            break;
                    }
                    userDTO.setRole(rolename);
                    userDTO.setTarget(userRepository.findAll().get(i).getTarget());
                    userDTO.setUserid(userRepository.findAll().get(i).getUserID().toString());
                    userDTO.setPassword(userRepository.findAll().get(i).getPassword());
                    userDTO.setSdt(userRepository.findAll().get(i).getPhonenumber());
                    if(userRepository.findAll().get(i).getBirthdate()!=null)
                    {
                        userDTO.setBirthdate(userRepository.findAll().get(i).getBirthdate().toString().substring(0,10));

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
        model.addAttribute("page",userDTO);
        model.addAttribute("userid",userDTO.getUserid());
        for (int i=0;i<imageRepository.findAll().size();i++)
        {
            if(imageRepository.findAll().get(i).getUser_id().equals(DataDto.user_id))
            {
                hinhanh = imageRepository.findAll().get(i).getImage_name();
            }
        }
        if(hinhanh.equals(""))
        {
            model.addAttribute("image","nothing.png");
        }
        else{
            model.addAttribute("image",hinhanh);
        }
        model.addAttribute("username",DataDto.user_name);
        DataDto.imagepicture="";
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "setting";
    }
    @RequestMapping("/updateuser")
    public String updateuser(@ModelAttribute UserDetailDto userEntity, Model model){
//        System.out.println(img.getOriginalFilename());

        String imgid = "";
        for(int i=0;i<imageRepository.findAll().size();i++)
        {
            if(imageRepository.findAll().get(i).getUser_id().equals(DataDto.user_id))
            {
                DataDto.oldimg = imageRepository.findAll().get(i).getImage_name();
                imgid = imageRepository.findAll().get(i).getImage_id();
                System.out.println("coimgid");
            }
        }
        if(imgid==null || imgid.length()==0)
        {
            if(DataDto.imagepicture == null || DataDto.imagepicture.length() == 0)
            {
                System.out.println("co1");
            }
            else {
                Image images = new Image(DataDto.imagepicture,DataDto.user_id);
                imageRepository.save(images);
                System.out.println("luuanh");
            }

        }
        else{
            if(DataDto.imagepicture == null || DataDto.imagepicture.length() == 0)
            {
                System.out.println("kosuuanh");
            }
            else
            {
                Image images = imageRepository.findById(imgid).orElseThrow();
                images.setImage_name(imagepicture);
                imageRepository.save(images);
                System.out.println("suaanh");
            }

        }
        User newuser = userRepository.findById(DataDto.user_id).orElseThrow();
        newuser.setFirstname(userEntity.getFirstname());
        newuser.setLastname(userEntity.getLastname());
        newuser.setPassword(userEntity.getPassword());
        newuser.setPhonenumber(userEntity.getSdt());
        newuser.setTarget(userEntity.getTarget());
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(userEntity.getBirthdate()+" 00:00", formatter);
        newuser.setBirthdate(dateTime);

        userRepository.save(newuser);
        //tìm cái id
//        userRepository.save(userEntity);
        List<GroupDto> list = new ArrayList<>();
//        list.add(new UserEntity("a","b"));
//        list.add(new UserEntity("c","b"));
        GroupDto groupDTO = new GroupDto();
       list= getnewlist(list);
        model.addAttribute("loadUser",list);
        model.addAttribute("groupdto",groupDTO);
        for(int i=0;i<userRepository.findAll().size();i++)
        {
            if(userRepository.findAll().get(i).getUserID().equals(DataDto.user_id))
            {
                DataDto.user_name=userRepository.findAll().get(i).getFirstname()+" "
                        +userRepository.findAll().get(i).getLastname();
            }
        }
        model.addAttribute("username",DataDto.user_name);
        boolean checked=false;
        String hinhanh = "";
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
