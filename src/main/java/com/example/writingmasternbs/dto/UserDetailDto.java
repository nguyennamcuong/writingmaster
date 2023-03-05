package com.example.writingmasternbs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDetailDto {
    String firstname;
    String lastname;
    String email;
    String  role;
    String mclass;
    String target;
    String userid;
    String imgpicture;
    String password;
    String sdt;
    String birthdate;
}
