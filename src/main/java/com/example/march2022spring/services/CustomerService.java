package com.example.march2022spring.services;

import com.example.march2022spring.dao.CustomerDAO;
import com.example.march2022spring.models.Customer;
import com.example.march2022spring.models.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private PasswordEncoder passwordEncoder;
    private CustomerDAO customerDAO;

    public void saveCustomer (CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setLogin(customerDTO.getLogin());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        customerDAO.save(customer);
    }

    public List<Customer> findAll(){
        return customerDAO.findAll();
    }

    public Customer findById (int id){
        return customerDAO.findById(id).orElseGet(Customer::new);
    }

    public Customer findCustomerByLogin (String customerLogin){
        return customerDAO.findCustomerByLogin(customerLogin);
    }
}
