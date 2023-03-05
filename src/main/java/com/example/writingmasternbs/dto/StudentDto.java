package com.example.writingmasternbs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentDto {
    String studentid;
    String studentname;
    String email;
    String studentphone;
    String studenttarget;
    String notes;
    String groupid;

    public StudentDto(String studentid, String studentname, String email, String studentphone, String studenttarget, String notes, String groupid) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.email = email;
        this.studentphone = studentphone;
        this.studenttarget = studenttarget;
        this.notes = notes;
        this.groupid = groupid;
    }
}
