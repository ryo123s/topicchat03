package com.example.demo.form;

import lombok.Data;

@Data
public class NoteForm {
	private String title;
	private String text;
	private String file;
	private String user_id;
	private String chat_id;
	private String chatname;
	private String group_id;
	private String groupname;
}
