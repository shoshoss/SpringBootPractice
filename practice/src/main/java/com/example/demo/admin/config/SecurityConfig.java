package com.example.demo.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
    	.authorizeHttpRequests(authz -> authz
    		.requestMatchers("/contact").permitAll()
    		.requestMatchers("/admin/signup").permitAll()
    		.requestMatchers("/admin/signin").permitAll()
    		.anyRequest().authenticated()
    		)
    	.formLogin(login -> login
			.usernameParameter("email")
            .passwordParameter("password")
            .loginProcessingUrl("/admin/signin")
			.loginPage("/admin/signin")
    		.defaultSuccessUrl("/admin/contacts")
    		.permitAll())
    	.logout(logout -> logout
    		.logoutSuccessUrl("/admin/signin")
    		.permitAll()
    	)
		.csrf(csrf -> csrf.disable());

    return http.build();
  }
  
  @Bean
  PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
}