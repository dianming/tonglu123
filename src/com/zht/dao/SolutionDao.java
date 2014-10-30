package com.zht.dao;

import java.util.List;

import com.zht.entity.Solution;

public interface SolutionDao {

	Integer add(Solution solution);

	List<Solution> queryAll();

	Solution queryById(Long sid);
	
	Integer putById(Solution solution);
}
