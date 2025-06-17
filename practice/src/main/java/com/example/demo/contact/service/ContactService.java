package com.example.demo.contact.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;

public interface ContactService {

	void saveContact(ContactForm contactForm);
	List<Contact> getAllContacts(); // 一覧取得用
	Optional<Contact> getContactById(Long id); // 詳細取得用
}
