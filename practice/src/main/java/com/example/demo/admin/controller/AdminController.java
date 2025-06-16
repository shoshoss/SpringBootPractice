package com.example.demo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.service.ContactService;

@Controller
public class AdminController {

	@Autowired
	private ContactService contactService;
	
	@GetMapping("/admin/contacts")
	public String showContactList(Model model) {
		List<Contact> contactList = contactService.getAllContacts();
		model.addAttribute("contactList", contactList);
		return "admin/contactList";
	}
}
