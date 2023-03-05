package com.example.writingmasternbs.dto;

import lombok.*;

import java.util.ArrayList;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DataGroupDto {
    private String groupname;
    private String groupdatecreate;
    private String groupdateend;
    private String grouppass;
    private String groupnote;

    private ArrayList<StudentDto> studentDTOS;
    private String groupid;
}
