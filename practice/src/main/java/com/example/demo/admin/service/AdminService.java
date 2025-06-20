package com.example.demo.admin.service;

import com.example.demo.admin.form.SignupForm;

public interface AdminService {
	
	boolean isEmailExists(String email);
	void saveAdmin(SignupForm adminForm);
}
