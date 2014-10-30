package com.zht.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zht.entity.Message;
import com.zht.entity.UserInfo;
import com.zht.entity.Users;
import com.zht.service.UsersService;
import com.zht.service.impl.UsersServiceImpl;

@Controller
@RequestMapping("/users")
@Scope("prototype")
public class UsersController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logs = Logger.getLogger(this.getClass().getPackage()
			.getName());

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private UsersService usersServiceImpl;

	/**
	 * 登录
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public String login(HttpServletRequest request,
			@ModelAttribute("user") Users users) {
		logs.debug("登录中");
		try {
			Users resultUsers = usersServiceImpl.queryByName(users.getUserName());
			if (resultUsers != null) {
				if (!resultUsers.getPassword().equals(users.getPassword())) {
					return "密码错误";
				} else {
					request.getSession().setAttribute("user", resultUsers);
					return "信息正确";
				}
			} else {
				return "用户名错误";
			}
		} catch (Exception e) {
			logs.debug("登录异常", e);
			return "登录失败！请稍后再试！！";
		}
	}

	/**
	 * 退出
	 */
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request) {
		logs.debug("退出！！");
		request.getSession().invalidate();
		return "redirect:/login.jsp";
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	/**
	 * 更新用户密码
	 */
	@RequestMapping(value = "/putUsers")
	public String putUsersPwd(HttpServletRequest request,
			@RequestParam("newPwd") String newPwd) {
		logs.debug("更新用户密码！");
		Users users = (Users) request.getSession().getAttribute("user");
		try {
			usersServiceImpl.putUsersPwd(users.getUserName(), newPwd);
		} catch (Exception e) {
			logs.debug("修改密码异常", e);
		}
		return "";
	}

	/**
	 * 显示用户信息
	 */
	@RequestMapping(value = "/Profile", method = RequestMethod.GET)
	@ResponseBody
	public String userInfo(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		try {
			if (user != null) {
				return objectMapper.writeValueAsString(usersServiceImpl
						.queryProfile(user.getUserId()));
			}
			return objectMapper.writeValueAsString("获取信息失败!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发布说说
	 */
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	@ResponseBody
	public String addMessage(@ModelAttribute("Message") Message message,HttpServletRequest request,
			@RequestParam(value="content",required=false)String content) {
		logs.debug("添加说说");
		Users user = (Users) request.getSession().getAttribute("user");
		if(user!=null){
			message.setUserId(user.getUserId());
			try {
				if(usersServiceImpl.addMessage(message,user.getPassword())!=1){
					return "0";
				};return "1";
			} catch (Exception e) {
				logs.debug("---->添加说说异常",e);
				e.printStackTrace();
			}
		}
		logs.debug("------->获取session"+user);
		return "0";
	}

	/**
	 * 刷新未读消息
	 */
	@RequestMapping(value = "/refresh")
	@ResponseBody
	public String refreshMessage(HttpServletRequest request) {
		logs.debug("刷新未读消息");
		Users user = (Users) request.getSession().getAttribute("user");
		Integer unreadTempMessageCount = null;
		if ((unreadTempMessageCount = usersServiceImpl.refreshTempMessage(user
				.getUserId())) != 0) {
			return unreadTempMessageCount.toString();
		}
		;
		return "0";
	}

	@RequestMapping(value = "/fsearch")
	public String delTempMessage() {
		return null;
	}

	@RequestMapping(value = "/recommendFriend")
	@ResponseBody
	public String recommendFriend(HttpServletRequest request) {
		logs.debug("个人首页");
		Users user = (Users) request.getSession().getAttribute("user");
		StringWriter sw = new StringWriter();
		List<UserInfo> userList = usersServiceImpl.queryRecommendFriend(user
				.getUserId());
		try {
			objectMapper.writeValue(sw, userList);
			return sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "连接出错";
		}
	}
}
