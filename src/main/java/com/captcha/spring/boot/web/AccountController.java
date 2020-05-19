package com.captcha.spring.boot.web;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.captcha.spring.boot.model.User;
import com.captcha.spring.boot.service.UserService;
import com.captcha.spring.boot.web.dto.UserRegistrationDto;

@Controller
public class AccountController {

	@Autowired
	private UserService userService;
	
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

	@GetMapping("/accountUpdate")
	public String getUserDetails(@ModelAttribute("user") UserRegistrationDto userDto, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(authentication.getName());
		if (null != user) {
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setUserName(user.getUserName());
		}
		return "accountUpdate";
	}
	
	   @PostMapping("/accountUpdate")
	    public String updateUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
	                                      BindingResult result){
		    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    User loginUser = userService.findByEmail(authentication.getName());
		    
 	        List<User> duplicateUser = userService.findByExistinguser(loginUser.getId(), userDto.getUserName(), userDto.getEmail());
	        if (!CollectionUtils.isEmpty(duplicateUser)){
	            result.rejectValue("email", null, "There is already an account registered with this user");
	        }

	        if (result.hasErrors()){
	            return "accountUpdate";
	        }
	        userDto.setId(loginUser.getId());
	        userService.save(userDto);
	        return "confirmUpdate";
	    }
	   
	   @GetMapping("/admin")
	    public String getAdminPage() {  
	    	return "admin";
	    } 
	   
	   @GetMapping("/tutorials")
	    public String getTutorialsPage() {  
	    	return "tutorials";
	    } 
}
