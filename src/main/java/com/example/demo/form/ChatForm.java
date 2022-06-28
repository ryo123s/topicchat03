package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * チャットを作成するためのフォーム
 * @author jinjinliangjie
 *
 */
@Data
public class ChatForm {
	@NotBlank(message = "チャットIDを入力してください")
	@Size(max=20, message="文字数20までで入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message="半角英数で入力してください")
	private String chat_id;
	@NotBlank(message = "チャット名を入力してください")
	@Size(max=30, message="30の間で入力してください")
	private String chatname;
	@Size(max=70, message="70文字以内で入力してください")
	private String comment;
	private String group_id;
	private String groupname;
	private String user_id;
}
