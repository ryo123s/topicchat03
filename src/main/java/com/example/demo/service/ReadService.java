package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ChatMemberEntity;
import com.example.demo.entity.GroupMemberEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.form.ChatForm;
import com.example.demo.form.MessageForm;
import com.example.demo.mapper.ReadMapper;

@Service
public class ReadService {
	@Autowired
	private ReadMapper readMapper;
	
	/**
	 * 未読登録処理
	 * @param messageForm
	 * @param userList
	 */
	@Transactional
	public void addRead(MessageForm messageForm, List<UserEntity> userList) {
		for(UserEntity list: userList) {
			if(messageForm.getUser_id().equals(list.getUser_id())){
			}else {
				messageForm.setRead_user_id(list.getUser_id());
				readMapper.addRead(messageForm);
			}
		}
	}
	
	@Transactional
	public void deleteReadByChatIdAndUserId(ChatForm chatform) {
		readMapper.deleteReadByChatIdAndUserId(chatform);
	}
	
	/**
	 * グループIDとユーザーIDからグループの未読数を取得
	 * @param groupMemberEntity
	 * @return
	 */
	@Transactional
	public int getUnreadCountByGroupIdAndUserId(GroupMemberEntity groupMemberEntity) {
		return readMapper.getUnreadCountByGroupIdAndUserId(groupMemberEntity);
	}
	
	/**
	 * グループリストの一件一件に未読の数をセット
	 * @param groupList
	 * @return
	 */
	@Transactional
	public List<GroupMemberEntity> getUnreadCountByGroupMemberEntityList(List<GroupMemberEntity> groupList){
		for(GroupMemberEntity list: groupList) {
			list.setCount_unread(getUnreadCountByGroupIdAndUserId(list));
		}
		return groupList;
	}
	
	/**
	 * チャットIDとユーザーIDからグループの未読数を取得
	 * @param chatMemberEntity
	 * @return
	 */
	@Transactional
	public int getUnreadCountByChatIdAndUserId(ChatMemberEntity chatMemberEntity) {
		return readMapper.getUnreadCountByChatIdAndUserId(chatMemberEntity);
	}
	
	/**
	 * チャットリストの一件一件に未読の数をセット
	 * @param chatList
	 * @return
	 */
	@Transactional
	public List<ChatMemberEntity> getUnreadCountByChatMemberEntityList(List<ChatMemberEntity> chatList){
		for(ChatMemberEntity list: chatList) {
			list.setCount_unread(getUnreadCountByChatIdAndUserId(list));
		}
		return chatList;
	}
}
