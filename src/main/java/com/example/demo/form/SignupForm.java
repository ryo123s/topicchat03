package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 新規登録のためのフォーム
 * @author jinjinliangjie
 *
 */
@Data
public class SignupForm {
	@NotBlank(message = "ユーザーIDを入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message="半角英数で入力してください")
	@Size(max=20, message="文字数20までで入力してください")
	private String user_id;
	@NotBlank(message = "ユーザー名を入力してください")
	@Size(max=30, message="文字数30までで入力してください")
	private String username;
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	@Size(max=70, message="文字数70までで入力してください")
	private String comment;
}
