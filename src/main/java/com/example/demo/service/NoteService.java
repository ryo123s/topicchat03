package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.NoteEntity;
import com.example.demo.form.NoteForm;
import com.example.demo.mapper.NoteMapper;

@Service
public class NoteService {
	@Autowired
	private NoteMapper noteMapper;
	
	@Transactional
	public void addNote(NoteForm noteForm) {
		noteMapper.addNote(noteForm);
	}
	
	@Transactional
	public List<NoteEntity> getNoteListByChatId(String chat_id){
		return noteMapper.getNoteListByChatId(chat_id);
	}
}
