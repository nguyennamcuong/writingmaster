package com.example.writingmasternbs.service;


import com.example.writingmasternbs.dto.TaskDTO;
import com.example.writingmasternbs.model.Task;
import com.example.writingmasternbs.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
public final TaskRepo taskRepo;
public List<TaskDTO> getListTask(List<TaskDTO> listTask){
    List<Task> list = taskRepo.findAll();
    for(int i=0;i<list.size();i++)
    {
        listTask.add(new TaskDTO(taskRepo.findAll().get(i).getTask_type(),taskRepo.findAll().get(i).getTask_about(), taskRepo.findAll().get(i).getDate_create(),taskRepo.findAll().get(i).getTeacher_id(),taskRepo.findAll().get(i).getTask_from(),taskRepo.findAll().get(i).getTask_to(),taskRepo.findAll().get(i).getDate_begin(),taskRepo.findAll().get(i).getDate_end(),taskRepo.findAll().get(i).getTask_content(), taskRepo.findAll().get(i).getGroup_id(), taskRepo.findAll().get(i).getId()));
    }
    return listTask;
    }
    public void createTask(TaskDTO taskDTO) {
        Date startdate = Calendar.getInstance().getTime();
        LocalDate date = LocalDate.ofInstant(startdate.toInstant(), ZoneId.systemDefault());
        Task task = Task.builder().
        task_type(taskDTO.getTask_type()).
                task_about(taskDTO.getTask_about()).
                date_create(date).
                teacher_id(taskDTO.getTeacher_id()).
                date_begin(taskDTO.getDate_begin()).
                date_end(taskDTO.getDate_end()).
                task_content(taskDTO.getTask_content()).
                task_from(taskDTO.getTask_from()).
                task_to(taskDTO.getTask_to()).
                group_id(taskDTO.getGroup_id()).
                id(taskDTO.getId()).
                build();
        taskRepo.save(task);
        log.info("Topic {} is " + task.getTask_about());
    }
    public void updateTask(TaskDTO taskDTO){
        Task task1 = Task.builder().
                task_type(taskDTO.getTask_type()).
                task_about(taskDTO.getTask_about()).
                date_create(taskDTO.getDate_create()).
                teacher_id(taskDTO.getTeacher_id()).
                date_begin(taskDTO.getDate_begin()).
                date_end(taskDTO.getDate_end()).
                task_content(taskDTO.getTask_content()).
                task_from(taskDTO.getTask_from()).
                task_to(taskDTO.getTask_to()).
                group_id(taskDTO.getGroup_id()).
                id(taskDTO.getId()).
                build();
        taskRepo.save(task1);
        log.info("Topic {} is " + task1.getTask_about());

    }
    public  void deleteTask(String id){
        taskRepo.deleteById(id);
    }
}
