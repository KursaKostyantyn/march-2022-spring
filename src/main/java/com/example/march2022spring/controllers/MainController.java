package com.example.march2022spring.controllers;

import com.example.march2022spring.dao.UserDAO;
import com.example.march2022spring.models.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class MainController {
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

    private UserDAO userDAO;

    //    @PostMapping("/users")
//    public void saveUser (@RequestBody User user){
//        System.out.println(user);
//        userDAO.save(user);
//        System.out.println(user);
//    }
    @PostMapping("/users")
    public void saveUser(@RequestBody User user) {
        userDAO.save(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userDAO.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        Optional<User> byId = userDAO.findById(id);
        User user = byId.orElseGet(() -> new User());
        return user;
    }

//    @GetMapping("/users/name/{name}")
//    public List<User> getUsersByName(@PathVariable String name) {
//        return userDAO.findUserByName(name);
//    }

    @GetMapping("/users/name/{name}")
    public List<User> getMeUsersByName (@PathVariable String name){
        return userDAO.findMeUserByName(name);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id){
        userDAO.deleteById(id);
//        User user = userDAO.findById(id).get();
//        userDAO.delete(user);
    }

    @PutMapping("/users/{id}")
    public List<User> updateUserById(@PathVariable int id, @RequestBody User user){
        User u= userDAO.findById(id).orElseGet(()->new User());
        System.out.println(u);
        if(u.getName()!=null){
            u.setName(user.getName());
            userDAO.save(u);
        }
        return userDAO.findAll();
    }
}
