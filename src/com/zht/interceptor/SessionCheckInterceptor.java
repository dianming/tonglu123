package com.zht.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zht.entity.Users;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter {
	
	Logger logs = Logger.getLogger(this.getClass().getPackage().getName());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logs.debug("有请求过来，正在处理！！！");
		String url = request.getServletPath();
		Users users = (Users) request.getSession().getAttribute("user");
		if (this.isPassUrl(url)) {
			if (users != null) {
				return true;
			} else {
				response.sendRedirect("/exception/login.jsp");
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
			logs.debug("拦截异常",ex);
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 过滤url,后缀如下的url可以通过，否者将会被检查是否登录
	 */
	private boolean isPassUrl(String url) {
		if (!url.endsWith(".js") && !url.endsWith("login")){
			return true;
		}
		return false;
	}
}
