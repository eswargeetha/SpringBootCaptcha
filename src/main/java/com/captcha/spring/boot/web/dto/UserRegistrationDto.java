package com.captcha.spring.boot.web.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.captcha.spring.boot.captcha.ValidReCaptcha;
import com.captcha.spring.boot.constraint.FieldMatch;

import javax.validation.constraints.AssertTrue;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
})
public class UserRegistrationDto {

	private Long id;
	
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @Email
    @NotEmpty
    private String email;

    @AssertTrue
    private Boolean terms;
    
    @NotEmpty
    @ValidReCaptcha
    private String reCaptchaResponse;

    public String getReCaptchaResponse() {
        return reCaptchaResponse;
    }

    public void setReCaptchaResponse(String reCaptchaResponse) {
        this.reCaptchaResponse = reCaptchaResponse;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
