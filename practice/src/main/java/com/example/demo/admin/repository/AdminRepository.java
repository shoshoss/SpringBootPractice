package com.example.demo.admin.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.admin.entity.Admin;

@Mapper
public interface AdminRepository extends JpaRepository<Admin, Long> {
	boolean existsByEmail(String email);
	
	@Select("select * from admins where email = #{email}")
    Optional<Admin> findByEmail(String email);
}
