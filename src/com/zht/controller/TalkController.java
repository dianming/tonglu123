package com.zht.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zht.entity.Message;
import com.zht.entity.Users;
import com.zht.service.TalkService;

@Controller
@RequestMapping("/talk")
public class TalkController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logs = Logger.getLogger(this.getClass().getPackage().getName());

	@Autowired
	private TalkService talkService;
	
	@RequestMapping(value= "/home",method=RequestMethod.GET)
	public String showHome(){
		return "home";
	}
	/**
	 * 添加一条说说
	 */
	@RequestMapping(value = "/addTalk",method=RequestMethod.POST)
	@ResponseBody
	public String addTalk(@ModelAttribute("Talk") Message talk,HttpServletRequest request,
			@RequestParam(value="content",required=false)String content) {
		logs.debug("添加说说");
		Users user = (Users) request.getSession().getAttribute("users");
		if(user!=null){
			talk.setUserId(user.getUserId());
			if(talkService.addTalk(talk,user.getPassword())!=1){
				return "0";
			};
			return "1";
		}return "1";
	}
	
	/**
	 * 取出未读消息
	 * @return messageUnreadCount
	 */
	@RequestMapping(value = "/queryMessage",method=RequestMethod.GET)
	public String queryNewMessage(){
		logs.debug("查询新消息");
		return null;
	}
}
