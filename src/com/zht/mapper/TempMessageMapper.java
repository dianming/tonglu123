package com.zht.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.zht.entity.TempMessage;

public interface TempMessageMapper {
	
	@Insert("INSERT INTO temp_message(user_id,read_status,message_id)VALUES(#{userId},#{readStatus},#{messageId})")
	@Options(useGeneratedKeys = true ,keyProperty="tempMessageId")
	public Integer add(TempMessage tempMessage);
	//public Integer add(int tempMessageId,int userId,int status,int messageId);
	
	@Delete("DELETE FROM temp_message WHERE user_id = #{0}")
	public Integer del(int userId);
	
	public Integer put(int userId);
	
	@Select("SELECT COUNT(temp_message_id) FROM temp_message WHERE user_id =#{0}")
	public Integer queryUserMessageCount(int userId);
}
