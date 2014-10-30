package com.zht.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zht.entity.Attention;

public interface AttentionMapper {
	
	/**
	 * 添加关注
	 * @param attention
	 * @return
	 */
	@Insert("INSERT INTO attention(user_id,user_by_id) VALUES(#{userId},#{userById})")
	public Integer addAttention(Attention attention);

	/**
	 * 取消关注
	 * @param userId
	 * @param userById
	 * @return
	 */
	@Delete("DELETE FROM attention WHERE user_id = #{0} AND user_by_id = #{1}")
	public Integer delAttention(int userId, int userById);
	
	/**
	 * 验证是否已经关注
	 * @return
	 */
	@Select("SELECT * from attention WHERE user_id = #{0} AND user_by_id = #{1}")
	public Attention checkRelation(int userId,int userById);
	
}
