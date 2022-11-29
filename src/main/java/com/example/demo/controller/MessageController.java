package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.ChatForm;
import com.example.demo.form.MessageForm;
import com.example.demo.service.ChatMemberService;
import com.example.demo.service.MessageService;
import com.example.demo.service.ReadService;

/**
 * メッセージのコントローラー
 * @author jinjinliangjie
 *
 */
@Controller
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private ReadService readService;
	@Autowired
	private ChatMemberService chatMemberService;
	
	/**
	 * メッセージ画面に遷移する
	 * @param model
	 * @param chatForm
	 * @param messageForm
	 * @return
	 */
	@GetMapping("/message/message")
	public String message(Model model, @ModelAttribute("chatForm")ChatForm chatForm, MessageForm messageForm) {
		readService.deleteReadByChatIdAndUserId(chatForm);
		model.addAttribute("messageList", messageService.getMessageByChatId(chatForm.getChat_id()));
		model.addAttribute("chatInfo", chatForm);
		return "/message/message";
	}
	
	/**
	 * メッセージ送信処理
	 * @param model
	 * @param messageForm
	 * @return
	 */
	@PostMapping("/addMessage")
	public String addMessage(Model model, @ModelAttribute("messageForm")MessageForm messageForm) {
		if(messageForm.getText() == "" && messageForm.getFile() == null) {
			model.addAttribute("messageList", messageService.getMessageByChatId(messageForm.getChat_id()));
			model.addAttribute("chatInfo", messageForm);
			return "/message/message";
		}
		messageService.addMessage(messageForm);
		messageForm.setMessage_id(messageService.getLastMessageId());
		readService.addRead(messageForm, chatMemberService.getChatMemberListByChatId(messageForm.getChat_id()));
		model.addAttribute("messageList", messageService.getMessageByChatId(messageForm.getChat_id()));
		model.addAttribute("chatInfo", messageForm);
		return "/message/message";
	}
}
