package com.example.march2022spring.services.helpers;

import com.example.march2022spring.models.User;
import com.example.march2022spring.models.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    public UserDTO convertUserToUserDTO (User user){
        return new UserDTO(user);
    }
}
