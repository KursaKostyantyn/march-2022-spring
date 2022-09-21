package com.example.march2022spring.dao;


import com.example.march2022spring.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {
    public Customer findCustomerByLogin(String login);
}
