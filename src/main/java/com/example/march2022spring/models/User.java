package com.example.march2022spring.models;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, message = "enter from 3 char")
    private String name;
    @NotNull
    private String password;

//    @Email
    private String email;
    private boolean isActivated = false;
    private String photo;
    private String role;


    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String name, String password, String email, String photo) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.photo = photo;
    }
//    public User(String name, String password, String role) {
//        this.name = name;
//        this.password = password;
//        this.role = role;
//    }

}
