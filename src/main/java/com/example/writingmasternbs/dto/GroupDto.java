package com.example.writingmasternbs.dto;

import java.util.List;
import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GroupDto {
    private String group_name;
    private  String teacher_name;
    private  List<MemberDto> memberDTOS;
    private  String group_id;
    private  String group_pass;
    private  String notes;
    private   String date_create;
    private  String date_end;
    private String begin_date;

    public GroupDto(String group_name, String teacher_name, List<MemberDto> memberDTOS, String group_id, String group_pass, String notes, String date_create, String date_end, String begin_date) {
        this.group_name = group_name;
        this.teacher_name = teacher_name;
        this.memberDTOS = memberDTOS;
        this.group_id = group_id;
        this.group_pass = group_pass;
        this.notes = notes;
        this.date_create = date_create;
        this.date_end = date_end;
        this.begin_date = begin_date;
    }

    public GroupDto(String group_name, String teacher_name, List<MemberDto> memberDTOS, String group_id, String group_pass, String notes) {
        this.group_name = group_name;
        this.teacher_name = teacher_name;
        this.memberDTOS = memberDTOS;
        this.group_id = group_id;
        this.group_pass = group_pass;
        this.notes = notes;
    }
}
