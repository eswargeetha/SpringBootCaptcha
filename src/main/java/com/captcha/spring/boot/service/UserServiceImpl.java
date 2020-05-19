package com.captcha.spring.boot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.captcha.spring.boot.model.Role;
import com.captcha.spring.boot.model.User;
import com.captcha.spring.boot.repository.UserRepository;
import com.captcha.spring.boot.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration){
        User user = new User();
        if ( null != registration.getId()) {
        	user.setId(registration.getId());
        }
        user.setName(registration.getName());
        user.setEmail(registration.getEmail());
        user.setUserName(registration.getUserName());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }    

    public User findByUserDetails(String userName, String email){
        return userRepository.findByUserNameOrEmail(userName, email);
    }
    
    public List<User> findByExistinguser(Long id, String userName, String email){
        return userRepository.findByIdNotAndUserNameOrIdNotAndEmail(id, userName, id, email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
