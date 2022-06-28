package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.ChatForm;
import com.example.demo.form.NoteForm;
import com.example.demo.service.NoteService;

@Controller
public class NoteController {
	@Autowired
	private NoteService noteService;
	
	@GetMapping("/note/note")
	public String note(Model model, @ModelAttribute("chatForm")ChatForm chatForm) {
		model.addAttribute("noteList", noteService.getNoteListByChatId(chatForm.getChat_id()));
		model.addAttribute("chatInfo", chatForm);
		return "/note/note";
	}
	
	@GetMapping("/note/addNote")
	public String addNote(Model model, @ModelAttribute("noteForm")NoteForm noteForm) {
		model.addAttribute("noteInfo", noteForm);
		return "/note/addNote";
	}
	
	@PostMapping("/addNote")
	public String tryAddNote(Model model, @ModelAttribute("noteForm")NoteForm noteForm) {
		noteService.addNote(noteForm);
		model.addAttribute("noteList", noteService.getNoteListByChatId(noteForm.getChat_id()));
		model.addAttribute("chatInfo", noteForm);
		return "/note/note";
	}
}
