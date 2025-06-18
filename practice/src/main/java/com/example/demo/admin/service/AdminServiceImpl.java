package com.example.demo.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;
import com.example.demo.contact.repository.ContactRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private ContactRepository contactRepository;

	// 一覧
	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}
	
	// 詳細
	@Override
	public Optional<Contact> getContactById(Long id) {
		return contactRepository.findById(id);
	}
	
	// 編集（問い合わせ情報の取得）
	@Override
	public ContactForm getEditContact(Long id) {
		// DBから情報の取得
		Optional<Contact> contactOpt = contactRepository.findById(id);
		Contact contact = contactOpt.get();
		
		// ContactFormオブジェクトを作成してプロパティを設定
		ContactForm form = new ContactForm();
		form.setId(id);
		form.setLastName(contact.getLastName());
		form.setFirstName(contact.getFirstName());
		form.setEmail(contact.getEmail());
		form.setPhone(contact.getPhone());
		form.setZipCode(contact.getZipCode());
		form.setAddress(contact.getAddress());
		form.setBuildingName(contact.getBuildingName());
		form.setContactType(contact.getContactType());
		form.setBody(contact.getBody());
		return form;
	}
}
