package com.example.demo.admin.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignupForm {
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password; 
}
