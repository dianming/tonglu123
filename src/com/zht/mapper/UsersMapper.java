package com.zht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zht.entity.UserInfo;
import com.zht.entity.Users;

public interface UsersMapper {
	/**
	 * 登录
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM users WHERE username = #{username}")
	public Users LoginByNamePwd(@Param("username")String username);
	
	/**
	 * 修改密码
	 * @param pwd
	 * @param id
	 * @return
	 */
	@Update("UPDATE users SET password=#{0} WHERE user_id=#{1}")
	public Integer updateUsersPwd(String pwd,int id);
	
	/**
	 * 根据UserId 查询用户
	 * @param userId
	 * @return
	 */
	@Select("SELECT * FROM users WHERE user_id =#{0}")
	public Users queryUsersByUserId(int userId);
	
	/**
	 * 被关注人数
	 * @return
	 */
	@Select("SELECT COUNT(u.user_id) FROM users u,attention a WHERE u.user_id = a.user_id AND u.user_id = #{0}")
	public int queryByAttentionCount(int userId);
	
	/**
	 * 查询关注者
	 * @return
	 */
	@Select("SELECT COUNT(a.user_by_id) FROM attention a WHERE a.user_by_id = #{0} ")
	public int queryAttentionCount(int userId);
	
	/**
	 * 微博条数
	 */
	@Select("SELECT COUNT(message_id) FROM messages WHERE user_id = #{0}")
	public int queryMessageCount(int userId);
	
	/**
	 * 推荐朋友查询
	 */
	@Select("SELECT * FROM user_info WHERE user_id NOT IN(SELECT user_id FROM attention WHERE user_by_id = #{0})")
	public List<UserInfo> queryRecommendFriend(int userId);
	
	/**
	 * 查询关注我的人 
	 * @param userId
	 * @return
	 */
	@Select("SELECT user_by_id FROM attention WHERE user_id = #{0}")
	public List<Integer> queryByUserId(int userId);
	
}
