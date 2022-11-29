package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.NoteEntity;
import com.example.demo.form.NoteForm;

@Mapper
public interface NoteMapper {
	@Insert("insert into note(title, text, file, user_id, chat_id, upddatetime) values(#{title}, #{text}, #{file}, #{user_id}, #{chat_id}, NOW())")
	void addNote(NoteForm noteForm);
	
	@Select("select note.id, note.user_id, note.title, note.text, note.file, note.chat_id, note.upddatetime, user.username from note inner join user on note.chat_id = #{chat_id} and note.user_id = user.user_id")
	List<NoteEntity> getNoteListByChatId(String chat_id);
}
