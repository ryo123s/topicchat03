package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * コンタクトを管理するためのフォーム
 * @author jinjinliangjie
 *
 */
@Data
public class ContactForm {
	private String user_id;
	@Size(max=20, message="文字数20までで入力してください")
	@NotBlank(message = "ユーザーIDを入力してください")
	private String contact_user_id;
	private String username;
	private String comment;
}
