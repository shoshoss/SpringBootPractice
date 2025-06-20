package com.example.demo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.entity.Admin;
import com.example.demo.admin.form.SignupForm;
import com.example.demo.admin.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public boolean isEmailExists(String email) {
		return adminRepository.existsByEmail(email);
	}
	
	@Override
	public void saveAdmin(SignupForm adminForm) {
		Admin admin = new Admin();
		
		admin.setLastName(adminForm.getLastName());
		admin.setFirstName(adminForm.getFirstName());
		admin.setEmail(adminForm.getEmail());
		admin.setPassword(adminForm.getPassword());

		adminRepository.save(admin);
	}
}
