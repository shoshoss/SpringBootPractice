package com.example.demo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.service.ContactService;

@Controller
public class AdminController {

	@Autowired
	private ContactService contactService;
	
	// 一覧
	@GetMapping("/admin/contacts")
	public String showContactList(Model model) {
		List<Contact> contactList = contactService.getAllContacts();
		model.addAttribute("contactList", contactList);
		return "admin/contactList";
	}
	
	// 詳細
	@GetMapping("/admin/contacts/{id}")
	public String showDetail(@PathVariable Long id, Model model) {
		model.addAttribute(contactService.getContactById(id).orElse(null));
		return "admin/contactDetail";
	}
	
	// 編集
	@GetMapping("/admin/contacts/{id}/edit")
	public String editContact(@PathVariable Long id, Model model) {
		model.addAttribute("contactForm", contactService.getEditContact(id));
		return "admin/contactEdit";
	}
}
