package com.zht.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zht.entity.Attention;
import com.zht.entity.Users;
import com.zht.service.impl.AttentionServiceImpl;

@Controller
@RequestMapping(value = "/attention")
public class AttentionController {
	@Autowired
	private AttentionServiceImpl attentionServiceImpl;
	private Logger logs = Logger.getLogger(this.getClass().getPackage()
			.getName());
	/**
	 * 添加关注
	 * @param request
	 * @param userById
	 * @return
	 */
	@RequestMapping(value = "/add/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public String addAttention(HttpServletRequest request,
			@RequestParam(value = "userById",required= false) Integer userById,
			@PathVariable("uid") Integer uid) {
		Users user = (Users) request.getSession().getAttribute("user");
		if (user != null) {
			try {
				int matchingCondition = attentionServiceImpl.addAttention(new Attention(uid,user.getUserId()));
				//return "关注完成!";
				return "--->"+matchingCondition;
			} catch (Exception e) {
				e.printStackTrace();
				logs.debug("--->关注异常",e);
			}
		}
		return "请先登录！";
	}
	
	/**
	 * 取消关注
	 * @param request
	 * @param userById
	 * @return
	 */
	@RequestMapping(value = "/cancel",method=RequestMethod.POST)
	@ResponseBody
	public String cancel(HttpServletRequest request,
			@RequestParam(value = "userById",required= false) Integer userById){
		Users user = (Users) request.getSession().getAttribute("users");
		Integer status=0;
		if(user!=null){
			status = attentionServiceImpl.cancelAttention(user.getUserId(), userById);
		}
		return status.toString();
	}
}
