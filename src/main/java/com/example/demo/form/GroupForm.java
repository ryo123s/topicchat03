package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * グループを作成するためのフォーム
 * @author jinjinliangjie
 *
 */
@Data
public class GroupForm {
	@NotBlank(message = "グループIDを入力してください")
	@Size(max=20, message="文字数20までで入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message="半角英数で入力してください")
	private String group_id;
	@NotBlank(message = "グループ名を入力してください")
	@Size(max=30, message="文字数30までで入力してください")
	private String groupname;
	@Size(max=70, message="70文字以内で入力してください")
	private String comment;
	private String user_id;
	private String contact_user_id;
}
