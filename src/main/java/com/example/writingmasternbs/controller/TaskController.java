package com.example.writingmasternbs.controller;


import com.example.writingmasternbs.dto.*;
import com.example.writingmasternbs.model.Image;
import com.example.writingmasternbs.model.Task;
import com.example.writingmasternbs.repository.*;
import com.example.writingmasternbs.service.AnswerService;
import com.example.writingmasternbs.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class TaskController {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;
    private final TaskService taskService;
    private final ImageRepository imageRepo;
    private final TaskRepo taskRepo;
    public List<ImageDTO> imagelist = new ArrayList<>();
    int imageid = 0;
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

    @RequestMapping(value = { "/list" })
    public String listTask(Model model) {
        List<TaskDTO> list = new ArrayList<>();
        taskService.getListTask(list);
        System.out.println(list.size());
        model.addAttribute("listTask", list);
        List<GroupDto> list2 = new ArrayList<>();
        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "taskPage";
    }
    @GetMapping("/getlist")
    public String getPage(Model model){
        TaskDTO taskDTO = new TaskDTO();
        model.addAttribute("task",taskDTO);
        List<GroupDto> list2 = new ArrayList<>();
        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "taskPage";
    }
    @RequestMapping("create")
    public String imageUpload(@RequestParam("pic1") MultipartFile[] img, Model model,
                              @RequestParam("task1") String write,
                              @RequestParam("task2") String about,
                              @RequestParam("task3") String from,
                              @RequestParam("task4") String to,
                              @RequestParam("myTextarea") String textarea)  {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTask_type(write);
        taskDTO.setTask_about(about);
        taskDTO.setTask_from(from);
        taskDTO.setTask_to(to);
        taskDTO.setTask_content(textarea);
                taskService.createTask(taskDTO);
        DataDto.imagelist.clear();
        try {
            int j = 0;
            if (img.length == 0){
                for (MultipartFile file : img) {
                    DataDto.imagelist.add(file.getOriginalFilename());
                    try {
                        String saveFile = new ClassPathResource("static/images").getFile().getAbsolutePath();
                        System.out.println(saveFile);
                        byte[] bytes = file.getBytes();
                        InputStream initialStream = file.getInputStream();
                        byte[] buffer = new byte[initialStream.available()];
                        initialStream.read(buffer);
                        String folder = saveFile + "\\" + file.getOriginalFilename();
                        File targetFile = new File(folder);
                        try (OutputStream outStream = new FileOutputStream(targetFile)) {
                            outStream.write(buffer);
                        }
                        ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    j++;
                }
            }else {

            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

        String taskid = "0";
        for(int i=0;i<taskRepo.findAll().size();i++)
        {
            taskid = taskRepo.findAll().get(i).getId().toString();
        }
        List<String> listimgage = new ArrayList<>();
        listimgage = DataDto.imagelist;
        for(int i=0;i<listimgage.size();i++)
        {
            Image image = new Image(listimgage.get(i).toString(),taskid);
            imageRepo.save(image);
        }
        /////Xong r nhe nhung can fix
        List<TaskDTO> list = new ArrayList<>();
        taskService.getListTask(list);
        model.addAttribute("listTask", list);
        List<GroupDto> list2 = new ArrayList<>();
        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "taskPage";
    }
    @RequestMapping("/pagecreate")
    public String pagecreateTopic(@ModelAttribute TaskDTO taskDTO,Model model){
        TaskDTO taskDTO1 = new TaskDTO();
        model.addAttribute("task",taskDTO1);
        List<GroupDto> list2 = new ArrayList<>();

        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "newtask";
    }
//    @RequestMapping("/create")
//    public String createTopic(@ModelAttribute TaskDTO taskDTO,Model model){
//        taskService.createTask(taskDTO);
//        String taskid = "0";
//        for(int i=0;i<taskRepo.findAll().size();i++)
//        {
//            taskid = taskRepo.findAll().get(i).getId().toString();
//        }
//        List<String> listimgage = new ArrayList<>();
//        listimgage = Data.imagelist;
//        for(int i=0;i<listimgage.size();i++)
//        {
//            Image image = new Image(listimgage.get(i).toString(),Long.parseLong(taskid));
//            imageRepo.save(image);
//        }
//        /////Xong r nhe nhung can fix
//        List<TaskDTO> list = new ArrayList<>();
//        taskService.getListTask(list);
//        model.addAttribute("listTask", list);
//        return "taskPage";
//    }
    @PutMapping("/update")
    public void updateTopic(TaskDTO taskDTO){

        taskService.updateTask(taskDTO);
    }
    @RequestMapping("/deletetask")
    public String deleteTopic(Task task, @RequestParam("id") String id, Model model){
                taskService.deleteTask(id);
        List<TaskDTO> list = new ArrayList<>();
        taskService.getListTask(list);
        System.out.println(list.size());
        model.addAttribute("listTask", list);
        List<GroupDto> list2 = new ArrayList<>();
        list2= getnewlist(list2);
        model.addAttribute("loadUser", list2);
        return "taskPage";
    }
}
//tutu create drop cai database no loi o o

