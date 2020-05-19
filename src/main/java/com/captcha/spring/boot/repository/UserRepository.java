package com.captcha.spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.captcha.spring.boot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

    User findByUserNameOrEmail(String username, String email);
    
    User findByUserName(String username);
    
    List<User> findByIdNotAndUserNameOrIdNotAndEmail(Long id, String username, Long loginId, String email);

}
