package com.example.writingmasternbs.dto;

import java.util.ArrayList;
import java.util.List;

public class ListStudent {
    private ArrayList<StudentDto> studentDTOS;

    public List<StudentDto> getList() {
        return studentDTOS;
    }

    public void setList(ArrayList<StudentDto> list) {
        this.studentDTOS = list;
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "studentDTOS=" + studentDTOS +
                '}';
    }
}
