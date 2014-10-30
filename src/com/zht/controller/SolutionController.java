/*package com.zht.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zht.entity.Solution;
import com.zht.service.SolutionService;

@Controller
@RequestMapping(value = "/solution")
public class SolutionController implements Serializable {

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	Logger logs = Logger.getLogger(this.getClass().getPackage().getName());

	@Autowired
	private SolutionService solutionService;
	
	*//**
	 * 显示添加页面
	 * 
	 * @return
	 *//*
	@RequestMapping(value = "/show")
	public String show() {
		logs.debug("显示日志页面");
		return "createtext";
	}

	*//**
	 * 添加文章
	 * 
	 * @param mav
	 * @param solution
	 * @return
	 *//*
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(ModelAndView mav,
			@ModelAttribute("Solution") Solution solution) {
		logs.debug("添加解决方案！" + solution.getTitle() + "---->"
				+ solution.getContent());
		mav.setViewName("index");
		try {
			solutionService.add(solution);
			return "完成";
		} catch (Exception e) {
			logs.debug("出现异常", e);
			return "失败";
		}
	}

	*//**
	 * 获取文章列表
	 * 
	 * @param mav
	 * @return
	 *//*
	@RequestMapping(value = "/showEssayList", method = RequestMethod.GET)
	public String essayList(ModelAndView mav,Model model) {
		logs.debug("显示文章列表");
//		mav.setViewName("/background/essaylist");
		List<Solution> solutionList = solutionService.queryAll();
//		mav.addObject("solutionList", solutionList);
//		return mav;
		model.addAttribute(solutionList);
		return "/background/essaylist";
	}

	@RequestMapping(value = "/showAnEssay/{sid}", method = RequestMethod.GET)
	public String showAnEssay(ModelAndView mav,Model model,
			@PathVariable("sid") Long sid) {
		logs.debug("查询一篇文章");
//		mav.setViewName("background/updateEssay");
		Solution solution = solutionService.queryById(sid);
		model.addAttribute(solution);
		mav.addObject("solution", solution);
		
//		return mav;
		return "background/updateEssay";
	}

	@RequestMapping(value = "/putAnEssay", method = RequestMethod.POST)
	@ResponseBody
	public String put(ModelAndView mav,
			@ModelAttribute("Solution") Solution solution) {
		logs.debug("修改方案！" + solution.getTitle());
		Map<String, String> map=new HashMap<String, String>();
		mav.setViewName("index");
		try {
			solutionService.putSolution(solution);
			return "成功";
		} catch (Exception e) {
			logs.debug("出现异常", e);
			return "失败";
		}
	}
}
*/