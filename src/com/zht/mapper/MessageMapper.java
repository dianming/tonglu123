package com.zht.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.zht.entity.Message;

public interface MessageMapper {
	
	@Insert("INSERT INTO messages(content,status,access_authority,user_id) VALUES(#{content},#{status},#{accessAuthority},#{userId})")
	@Options(useGeneratedKeys = true ,keyProperty="messageId")
	public Integer add(Message message);
	
	@Delete("DELETE FROM messages WHERE id=#{0}")
	public Integer del(int id);
	
}