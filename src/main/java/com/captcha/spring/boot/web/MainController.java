package com.captcha.spring.boot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.captcha.spring.boot.web.dto.UserRegistrationDto;

@Controller
public class MainController {
	

    @GetMapping("/")
    public String root() {  
    	return "redirect:/home";
    } 

    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("login", new UserRegistrationDto()); 
        return "login";
    }
    
     
    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }
    
    @GetMapping(value="/logout")  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/login";  
     }  
}
