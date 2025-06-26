package com.example.demo.admin.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.admin.entity.Admin;
import com.example.demo.admin.repository.AdminRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

		private final AdminRepository adminRepository;
		
		public AppUserDetailsService(AdminRepository adminRepository) {
			this.adminRepository = adminRepository;
		}
		
		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			Admin appUser = adminRepository.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
			
			return User.builder()
					.username(appUser.getEmail())
					.password(appUser.getPassword())
					.roles("USER")
					.build();
		}
}
