package com.example.demo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.admin.service.AdminService;
import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	// 一覧
	@GetMapping("/admin/contacts")
	public String showContactList(Model model) {
		List<Contact> contactList = adminService.getAllContacts();
		model.addAttribute("contactList", contactList);
		return "admin/contactList";
	}
	
	// 詳細
	@GetMapping("/admin/contacts/{id}")
	public String showDetail(@PathVariable Long id, Model model) {
		model.addAttribute(adminService.getContactById(id).orElse(null));
		return "admin/contactDetail";
	}
	
	// 編集
	@GetMapping("/admin/contacts/{id}/edit")
	public String editContact(@PathVariable Long id, Model model) {
		model.addAttribute("contactForm", adminService.getEditContact(id));
		return "admin/contactEdit";
	}
	
	// 更新
	@PostMapping("/admin/contacts/{id}/edit")
	public String updateContact(@PathVariable Long id,
								@Validated @ModelAttribute("contactForm") ContactForm contactForm,
								BindingResult result,
								Model model) {
		if (result.hasErrors()) {
			return "admin/contactEdit";
		}
		
		adminService.updateContact(id, contactForm);
		return "redirect:/admin/contacts";
	}
	
	// 削除
	@GetMapping("/admin/contacts/{id}/delete")
	public String deleteContact(@PathVariable Long id) {
		adminService.deleteContact(id);
		return "redirect:/admin/contacts";
	}
}
