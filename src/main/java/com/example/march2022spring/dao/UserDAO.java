package com.example.march2022spring.dao;

import com.example.march2022spring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    List<User> findUserByName(String name);

    @Query("select u from User u where u.name=:name")
    List<User> findMeUserByName (String name);
}
