package com.example.writingmasternbs.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userID;
    private String roleID;
    private String firstname;
    String lastname;
    String email;
    String password;
    Boolean activeaccount;
    String gender;
    String address;
    LocalDateTime birthdate;
    String phonenumber;
    LocalDateTime timecreate;
    LocalDateTime expiry;
    int numberoffail;
    String target;
    String notes;

    public User(String roleID, String email, String password) {
        this.roleID = roleID;
        this.email = email;
        this.password = password;
    }
}