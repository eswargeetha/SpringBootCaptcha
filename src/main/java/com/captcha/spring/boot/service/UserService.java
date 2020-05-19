package com.captcha.spring.boot.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.captcha.spring.boot.model.User;
import com.captcha.spring.boot.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByUserDetails(String username, String email);

    User save(UserRegistrationDto registration);
        
    User findByEmail(String email);
    
    List<User> findByExistinguser(Long id, String username, String email);
}
