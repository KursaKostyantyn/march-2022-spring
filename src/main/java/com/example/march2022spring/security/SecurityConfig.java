package com.example.march2022spring.security;

import com.example.march2022spring.dao.UserDAO;
import com.example.march2022spring.models.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDAO userDAO;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("manager").password("{noop}manager").roles("MANAGER");
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user=userDAO.findUserByNameSecurity(username);

                return new org.springframework.security.core.userdetails.User(user.getName(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http = http.csrf().disable();
        http = http.cors().disable();
        http = http.authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers("/users/**").hasAnyRole("MANAGER","ADMIN")
                .and();
        http = http.httpBasic()
                .and();
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
    }
}
