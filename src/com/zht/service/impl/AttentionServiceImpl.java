package com.zht.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zht.dao.impl.AttentionImpl;
import com.zht.dao.impl.UsersImpl;
import com.zht.entity.Attention;
import com.zht.entity.Users;
import com.zht.service.AttentionService;

@Service("attentionServlet")
@Scope(value="prototype")
public class AttentionServiceImpl implements AttentionService {

	@Autowired
	private AttentionImpl attentionImpl;

	@Autowired
	private UsersImpl usersImpl;

	/**
	 * 添加关注 0 添加失败 1 成功 2 关注用户不存在 3已经被关注
	 */
//	@Transactional
	public Integer addAttention(Attention attention) {
		try {
			Users byUser = usersImpl.queryUsersByUserId(attention.getUserId());
			Attention resultAttention = attentionImpl.checkRelation(
					attention.getUserId(), attention.getUserById());
			if (byUser != null) {
				if (resultAttention == null) {
					return attentionImpl.add(attention);
				}
				return 3;
			}
			return 2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 取消关注0 失败 1 成功 2你没有关注此用户
	 */
	@Override
	public Integer cancelAttention(int userId, int userById) {
		try {
			Attention resultAttention = attentionImpl.checkRelation(userId,userById);
			if(resultAttention!=null){
				return attentionImpl.del(userId, userById);
			}return 2;
		} catch (Exception e) {
			e.printStackTrace();
		}return 0;
	}
}
