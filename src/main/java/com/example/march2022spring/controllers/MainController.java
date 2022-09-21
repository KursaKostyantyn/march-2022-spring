package com.example.march2022spring.controllers;

import com.example.march2022spring.models.Customer;
import com.example.march2022spring.models.CustomerDTO;
import com.example.march2022spring.services.CustomerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private CustomerService customerService;
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @PostMapping("/register")
    public void register(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomerDTO customerDTO) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerDTO.getLogin(), customerDTO.getPassword())
        );
        if (authenticate != null) {
            String jwtToken = Jwts.builder()
                    .setSubject(authenticate.getName())
                    .signWith(SignatureAlgorithm.HS512, "kursa".getBytes())
                    .compact();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwtToken);
            return new ResponseEntity<>("you are loged in", headers, HttpStatus.OK);
        }
        return new ResponseEntity<>("bad credentials", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/secure")
    public List<Customer> getSecuredCustomer(){
        return customerService.findAll();
    }

}
