package com.example.demo.admin.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;

public interface AdminService {

	List<Contact> getAllContacts(); // 一覧取得用
	Optional<Contact> getContactById(Long id); // 詳細取得用
	ContactForm getEditContact(Long id); // 編集用
}
