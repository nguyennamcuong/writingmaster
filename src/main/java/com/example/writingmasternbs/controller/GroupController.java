package com.example.writingmasternbs.controller;

import com.example.writingmasternbs.dto.*;

import com.example.writingmasternbs.model.Group;
import com.example.writingmasternbs.model.Member;
import com.example.writingmasternbs.repository.GroupRepository;
import com.example.writingmasternbs.repository.ImageRepository;
import com.example.writingmasternbs.repository.MemberRepository;
import com.example.writingmasternbs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Controller

public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ImageRepository imageRepository;
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
    public List<GroupDto> getnewlist1(List<GroupDto> list,String id){
        for(int i=0;i < groupRepository.findAll().size();i++)
        {
            //phanchinhsua
            if(groupRepository.findAll().get(i).getTeacher_id().equals(DataDto.user_id))
            {
                if(groupRepository.findAll().get(i).getGroup_id().equals(id))
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
        }
        return list;
    }

    @RequestMapping("createnewgroup")
    public String getcheck(Model model,
                           @RequestParam("groupname2") String groupname,
                           @RequestParam("groupass2") String groupass2,
                           @RequestParam("groupnote2") String groupnote2,
                           @RequestParam("datecreate") String datecreate2,
                           @RequestParam("dateend") String dateend2
                           ){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        LocalDateTime todaydate = LocalDateTime.now();
        Date datecreate,dateend;
        try {
            datecreate= df.parse(datecreate2);
            dateend= df.parse(dateend2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Group Group = new Group(groupname,groupass2,
                DataDto.user_id,groupnote2,todaydate,datecreate,dateend);
        groupRepository.save(Group);
        List<GroupDto> list = new ArrayList<>();
//        list.add(new UserEntity("a","b"));
//        list.add(new UserEntity("c","b"));
          list =   getnewlist(list);
        model.addAttribute("loadUser",list);
        model.addAttribute("username", DataDto.user_name);
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


    @GetMapping("/{id}")
    public String addNewgroup(@PathVariable String id, Model model){
        String groupname="";

        ArrayList<StudentDto> studentDTOS = new ArrayList<>();
        ListStudent dataDTOList = new ListStudent();
        for(int i=0;i<groupRepository.findAll().size();i++)
        {
            if(groupRepository.findAll().get(i).getGroup_id().equals(id))
            {
                groupname=groupRepository.findAll().get(i).getGroup_name();
                model.addAttribute("groupname",groupname);
                model.addAttribute("groupdatecreate",groupRepository.findAll().get(i).getBegin_date().toString().substring(0,10));
                model.addAttribute("groupdateend",groupRepository.findAll().get(i).getExpiry_date().toString().substring(0,10));
                model.addAttribute("grouppass",groupRepository.findAll().get(i).getGroup_pass());
                model.addAttribute("groupnote",groupRepository.findAll().get(i).getNote());

                for(int j=0;j<userRepository.findAll().size();j++)
                {
                    if(groupRepository.findAll().get(i).getTeacher_id().equals(userRepository.findAll().get(j).getUserID())
                            ||userRepository.findAll().get(j).getRoleID().equals("1"))
                    {

                    }
                    else{
                        int dem=0;

                        for(int k=0;k<memberRepository.findAll().size();k++)
                        {
                            if(memberRepository.findAll().get(k).getGroup_id().equals(id))
                            {
                                if(memberRepository.findAll().get(k).getLearner_id().equals(userRepository.findAll().get(j).getUserID()))
                                {
                                    dem=1;
                                }

                            }
                        }
                        if(dem==0)
                        {
                            studentDTOS.add(new StudentDto(userRepository.findAll().get(j).getUserID().toString(),
                                    userRepository.findAll().get(j).getFirstname()+" "+userRepository.findAll().get(j).getLastname(),
                                    userRepository.findAll().get(j).getEmail(),userRepository.findAll().get(j).getPhonenumber(),
                                    userRepository.findAll().get(j).getTarget(),userRepository.findAll().get(j).getNotes(),
                                    String.valueOf(id)
                            ));
                        }
                    }
                }
//                model.addAttribute("loadliststudent",studentDTOS);
                dataDTOList.setList(studentDTOS);
                model.addAttribute("loadlistdata",dataDTOList);
            }
        }
        model.addAttribute("username",DataDto.user_name);
        String hinhanh="";
        boolean checked = false;
        for (int i=0;i<imageRepository.findAll().size();i++)
        {
            if(imageRepository.findAll().get(i).getUser_id().equals(DataDto.user_id))
            {
                hinhanh = imageRepository.findAll().get(i).getImage_id();
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
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "studentGroup";
    }
    @GetMapping("/")
    public String getallcomment(Model model){
        List<GroupDto> list = new ArrayList<>();
//        list.add(new UserEntity("a","b"));
//        list.add(new UserEntity("c","b"));
        list =   getnewlist(list);
        model.addAttribute("loadUser",list);
        model.addAttribute("username", DataDto.user_name);
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
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String processQuery(@ModelAttribute ListStudent dataDTO, Model model) {
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("loadlistdata", dataDTO);

        for(int i=0;i<dataDTO.getList().size();i++)
        {
            if(dataDTO.getList().get(i).getStudentid()==null)
            {

            }
            else{
                for(int j=0;j<userRepository.findAll().size();j++)
                {
                    if(userRepository.findAll().get(j).getUserID().equals(dataDTO.getList().get(i).getStudentid()))
                    {
                        Member memberEntity;
                        memberEntity = new Member(dataDTO.getList().get(i).getStudentid(),
                                dataDTO.getList().get(i).getGroupid(), now);
                        memberRepository.save(memberEntity);
                    }
                }
            }
        }
        List<GroupDto> list = new ArrayList<>();
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
                hinhanh = imageRepository.findAll().get(i).getImage_id();
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

    @RequestMapping("editgroup")
    public String editgroup( Model model,@RequestParam("id")String id){
        System.out.println(id);
        String groupname="";
        ArrayList<StudentDto> studentDTOS = new ArrayList<>();
        ListStudent dataDTOList = new ListStudent();
        DataGroupDto dataGroupDTO = new DataGroupDto();
        for(int i=0;i<groupRepository.findAll().size();i++)
        {
            if(groupRepository.findAll().get(i).getGroup_id().equals(id))
            {
                groupname=groupRepository.findAll().get(i).getGroup_name();
                dataGroupDTO.setGroupname(groupname);
                dataGroupDTO.setGroupdateend(groupRepository.findAll().get(i).getExpiry_date().toString().substring(0,10));
                dataGroupDTO.setGroupdatecreate(groupRepository.findAll().get(i).getBegin_date().toString().substring(0,10));
                dataGroupDTO.setGroupnote(groupRepository.findAll().get(i).getNote());
                dataGroupDTO.setGrouppass(groupRepository.findAll().get(i).getGroup_pass());
                dataGroupDTO.setGroupid(groupRepository.findAll().get(i).getGroup_id());
                for(int j=0;j<userRepository.findAll().size();j++)
                {
                    if(groupRepository.findAll().get(i).getTeacher_id().equals(userRepository.findAll().get(j).getUserID())
                            ||userRepository.findAll().get(j).getRoleID().equals("1"))
                    {

                    }
                    else{
                        int dem=0;

                        for(int k=0;k<memberRepository.findAll().size();k++)
                        {
                            if(memberRepository.findAll().get(k).getGroup_id().equals(id))
                            {
                                if(memberRepository.findAll().get(k).getLearner_id().equals(userRepository.findAll().get(j).getUserID()))
                                {
                                    dem=1;
                                }

                            }
                        }
                        if(dem==1)
                        {
                            studentDTOS.add(new StudentDto(userRepository.findAll().get(j).getUserID().toString(),
                                    userRepository.findAll().get(j).getFirstname()+" "+userRepository.findAll().get(j).getLastname(),
                                    userRepository.findAll().get(j).getEmail(),userRepository.findAll().get(j).getPhonenumber(),
                                    userRepository.findAll().get(j).getTarget(),userRepository.findAll().get(j).getNotes(),
                                    String.valueOf(id)
                            ));
                        }
                    }
                }
//                model.addAttribute("loadliststudent",studentDTOS);
                dataDTOList.setList(studentDTOS);
                model.addAttribute("loadlistdata",dataDTOList);
                dataGroupDTO.setStudentDTOS(studentDTOS);
                model.addAttribute("listdatagroup",dataGroupDTO);
            }
        }
        model.addAttribute("username",DataDto.user_name);
        String hinhanh="";
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
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "editGroup";
    }
    @RequestMapping(value = "/editedgroup", method = RequestMethod.POST)
    public String getEditGroup(@ModelAttribute DataGroupDto dataDTO, Model model) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate= df.parse(dataDTO.getGroupdatecreate());
            Date endDate= df.parse(dataDTO.getGroupdateend());
            System.out.println(dtf.format(now));
            model.addAttribute("loadlistdata", dataDTO);

            for(int j=0;j<groupRepository.findAll().size();j++)
            {
                if(groupRepository.findAll().get(j).getGroup_id().equals(dataDTO.getGroupid()))
                {
                    Group groupEntity = groupRepository.findById(dataDTO.getGroupid()).orElseThrow();
                    groupEntity.setGroup_pass(dataDTO.getGrouppass());
                    groupEntity.setGroup_name(dataDTO.getGroupname());
                    groupEntity.setExpiry_date(endDate);
                    groupEntity.setBegin_date(startDate);
                    groupEntity.setNote(dataDTO.getGroupnote());
                    groupRepository.save(groupEntity);
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<GroupDto> list = new ArrayList<>();
        getnewlist(list);
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

    @RequestMapping("/groupDdetail")
    public String getGroupDetail(Model model,@RequestParam("id") String id){
        List<GroupDto> list = new ArrayList<>();
//        list.add(new UserEntity("a","b"));
//        list.add(new UserEntity("c","b"));
        list= getnewlist1(list,id);
        model.addAttribute("loadUser", list);

        List<GroupDto> list2 = new ArrayList<>();
//        list.add(new UserEntity("a","b"));
//        list.add(new UserEntity("c","b"));
        list2= getnewlist(list2);
        model.addAttribute("loadUser2", list2);
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
        return "groupDetail";
    }
}
