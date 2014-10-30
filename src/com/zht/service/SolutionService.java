package com.zht.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zht.dao.impl.SolutionImpl;
import com.zht.entity.Solution;
import com.zht.wordapi.StyleException;

/**
 * 2014-06-08
 * 
 * @author admin
 * 
 */
@Service
// @Component("SolutionService")
// @Controller("SolutionService")
public class SolutionService {

	Logger logs = Logger.getLogger(this.getClass().getPackage().getName());

	@Autowired
	private SolutionImpl solutionImpl;

	public Integer add(Solution solution) {
		return solutionImpl.add(solution);
	}
	
	public List<Solution> queryAll() {
		return solutionImpl.queryAll();
	}
	
	public Solution queryById(Long sid){
		return solutionImpl.queryById(sid);
	};
	
	public Integer putSolution(Solution solution)throws Exception{
		try {
			return solutionImpl.putById(solution);
		} catch (Exception e) {
			logs.debug("异常",e);
			throw new StyleException("对象不可以空");
		}
	}
}
