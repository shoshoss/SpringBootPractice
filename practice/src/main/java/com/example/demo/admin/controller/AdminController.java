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

import com.example.demo.admin.form.SigninForm;
import com.example.demo.admin.form.SignupForm;
import com.example.demo.admin.service.AdminService;
import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;
import com.example.demo.contact.service.ContactService;

@Controller
public class AdminController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/signup")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		
		return "/admin/signup";
	}
	
	@PostMapping("/admin/signup")
	public String admin(@Validated @ModelAttribute("signupForm") SignupForm adminForm,
									BindingResult result) {
		if (result.hasErrors()) {
			return "/admin/signup";
		}
		
		adminService.saveAdmin(adminForm);
		
		return "/admin/completion";
	}
	
	@GetMapping("/admin/signin")
	public String signin(Model model) {
		model.addAttribute("signinForm", new SigninForm());
		
		return "/admin/signin";
	}
	
	/**
	 * お問い合わせの一覧を取得
	 * @param model
	 * @return 一覧リスト
	 */
	@GetMapping("/admin/contacts")
	public String showContactList(Model model) {
		List<Contact> contactList = contactService.getAllContacts();
		model.addAttribute("contactList", contactList);
		return "admin/contactList";
	}
	
	/**
	 * お問い合わせの詳細を取得
	 * @param id
	 * @param model
	 * @return 詳細画面
	 */
	@GetMapping("/admin/contacts/{id}")
	public String showDetail(@PathVariable Long id, Model model) {
		model.addAttribute(contactService.getContactById(id).orElse(null));
		return "admin/contactDetail";
	}
	
	/**
	 * お問い合わせを編集する情報を取得
	 * @param id
	 * @param model
	 * @return 編集画面
	 */
	@GetMapping("/admin/contacts/{id}/edit")
	public String editContact(@PathVariable Long id, Model model) {
		model.addAttribute("contactForm", contactService.getEditContact(id));
		return "admin/contactEdit";
	}
	
	/**
	 * お問い合わせの更新
	 * @param id
	 * @param contactForm
	 * @param result
	 * @param model
	 * @return 問い合わせ一覧へ
	 */
	@PostMapping("/admin/contacts/{id}/edit")
	public String updateContact(@PathVariable Long id,
								@Validated @ModelAttribute("contactForm") ContactForm contactForm,
								BindingResult result,
								Model model) {
		if (result.hasErrors()) {
			return "admin/contactEdit";
		}
		
		contactService.updateContact(id, contactForm);
		return "redirect:/admin/contacts";
	}
	
	/**
	 * お問い合わせの削除
	 * @param id
	 * @return 問い合わせ一覧へ
	 */
	@GetMapping("/admin/contacts/{id}/delete")
	public String deleteContact(@PathVariable Long id) {
		contactService.deleteContact(id);
		return "redirect:/admin/contacts";
	}
	
	
}
