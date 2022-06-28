package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoteEntity {
	private int id;
	private String user_id;
	private String username;
	private String title;
	private String text;
	private String file;
	private String chat_id;
	private LocalDateTime upddatetime;
}
