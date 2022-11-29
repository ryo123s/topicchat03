package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.ChatMemberEntity;
import com.example.demo.entity.GroupMemberEntity;
import com.example.demo.form.ChatForm;
import com.example.demo.form.MessageForm;

@Mapper
public interface ReadMapper {
	/**
	 * メッセージ送信時の未読登録
	 * @param messageForm
	 */
	@Insert("insert into message_read(message_id, group_id, chat_id, user_id) values(#{message_id}, #{group_id}, #{chat_id}, #{read_user_id})")
	void addRead(MessageForm messageForm);
	
	@Delete("delete from message_read where chat_id = #{chat_id} and user_id = #{user_id}")
	void deleteReadByChatIdAndUserId(ChatForm chatForm);
	
	@Select("select count(*) from message_read where group_id = #{group_id} and user_id = #{user_id}")
	int getUnreadCountByGroupIdAndUserId(GroupMemberEntity groupMemberEntity);
	
	@Select("select count(*) from message_read where chat_id = #{chat_id} and user_id = #{user_id}")
	int getUnreadCountByChatIdAndUserId(ChatMemberEntity chatMemberEntity);
}
