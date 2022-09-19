package com.example.march2022spring.models.dto;

import com.example.march2022spring.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String photo;

    public UserDTO (User user){
        this.id = user.getId();;
        this.name = user.getName();
        this.email= user.getEmail();
        this.photo=user.getPhoto();
    }
}
