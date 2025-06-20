package com.example.demo.contact.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;

public interface ContactService {

	void saveContact(ContactForm contactForm);
	
	/**
	 * お問い合わせ一覧を取得
	 * @return お問い合わせ一覧
	 */
	List<Contact> getAllContacts();
	
	/**
	 * 詳細情報を取得
	 * @param id
	 * @return お問い合わせの詳細情報
	 */
	Optional<Contact> getContactById(Long id);
	
	/**
	 * 編集画面で編集情報を取得
	 * @param id
	 * @return 編集情報
	 */
	ContactForm getEditContact(Long id);
	
	/**
	 * 問い合わせの更新
	 * @param id
	 * @param contactForm
	 */
	void updateContact(Long id, ContactForm contactForm);
	
	/**
	 * 問い合わせの削除
	 * @param id
	 */
	void deleteContact(Long id);
}
