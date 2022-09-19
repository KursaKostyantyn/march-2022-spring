package com.example.march2022spring.services;

import com.example.march2022spring.dao.UserDAO;
import com.example.march2022spring.models.User;
import com.example.march2022spring.models.dto.UserDTO;
import com.example.march2022spring.services.helpers.UserHelper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;
    private UserHelper userHelper;
    private MailService mailService;

    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) throws Exception {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        if (user != null) {
            userDAO.save(user);
            if(user.getId()!=0){
//                mailService.sendMailForActivate(user,null);
            }
        } else {
            throw new Exception("user is null");
        }

    }

    public List<UserDTO> getUsers() {
        return userDAO.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO getUserById(int id) {
        Optional<User> byId = userDAO.findById(id);
        UserDTO user = userHelper.convertUserToUserDTO(byId.orElseGet(() -> new User()));
        return user;
    }

    public List<UserDTO> getUsersByName(String name) {
        return userDAO.findUserByName(name)
                .stream()
                .map(user -> userHelper.convertUserToUserDTO(user))
                .collect(Collectors.toList());
    }

    public void deleteUserById(int id){
        userDAO.deleteById(id);
    }

    public List<UserDTO> updateUserById (int id, User user){
        System.out.println("tut 4to to");
        User u = userDAO.findById(id).orElseGet(()->new User());

        System.out.println(u);
        if (u.getName()!=null){
            System.out.println(user);
            user.setId(u.getId());
            userDAO.save(user);
        }
        return userDAO.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public void activateUser(int id){
        User user =userDAO.findById(id).orElseGet(()->new User());
        if(user.getName()!=null){
            user.setActivated(true);
            userDAO.save(user);
        }
    }

    public void saveUserWithFile(User user, File file) throws Exception {
        System.out.println(user);
        if (user != null) {
            userDAO.save(user);
            if(user.getId()!=0){
                mailService.sendMailForActivate(user,file);
            }
        } else {
            throw new Exception("user is null");
        }
    }

}
