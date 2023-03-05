package com.example.writingmasternbs.dto;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegisterDto {
    String email;
    String password;
    String roleid;
}
