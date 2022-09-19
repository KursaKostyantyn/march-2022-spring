package com.example.march2022spring.controllers;

import com.example.march2022spring.dao.UserDAO;
import com.example.march2022spring.models.User;
import com.example.march2022spring.models.dto.UserDTO;
import com.example.march2022spring.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    //    List<User> users=new ArrayList(){{
//        this.add(new User(1,"vasya"));
//        this.add(new User(2,"kolya"));
//        this.add(new User(3,"olya"));
//        this.add(new User(4,"masha"));
//    }};
//       ;
//
//    @GetMapping({"/users"})
//    public List<User> hello() {
//
//        return users;
//    }
//
//    @GetMapping({"/users/{id}"})
//    public User getUserById (@PathVariable int id){
//        User user = null;
//        for (User u : users) {
//            if(u.getId()==id)user=u;
//        }
//        return  user;
//    }
//    @PostMapping("/users")
//    public void saveUser (@RequestBody User user){
//        System.out.println(user);
//        userDAO.save(user);
//        System.out.println(user);
//    }

    @PostMapping("")
    public void saveUser(@RequestBody @Valid User user) throws Exception {
        userService.saveUser(user);
    }

    @GetMapping("")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/name/{name}")
    public List<UserDTO> getUsersByName(@PathVariable String name) {
        return userService.getUsersByName(name);
    }

//    @GetMapping("/name/{name}")
//    public List<User> getMeUsersByName(@PathVariable String name) {
//        return userDAO.findMeUserByName(name);
//    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
//        User user = userDAO.findById(id).get();
//        userDAO.delete(user);
    }

    @PutMapping("/{id}")
    public List<UserDTO> updateUserBId(@PathVariable int id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }

    @GetMapping("/activate")
    public void activateUser(@RequestParam int id) {
        userService.activateUser(id);
    }

    @PostMapping("/userWithPhoto")
    public void saveUserWithPhoto(@RequestParam String name,
                                  @RequestParam String password,
                                  @RequestParam String email,
                                  @RequestParam MultipartFile photo) throws Exception {
        String originalFilename = photo.getOriginalFilename();
        String pathToSaveFile = System.getProperty("user.home") + File.separator + "ppp" + File.separator + originalFilename;
        File dest = new File(pathToSaveFile);
        photo.transferTo(dest);
        User user=new User(name,password,email,originalFilename);
        userService.saveUserWithFile(user, dest);

    }

}
