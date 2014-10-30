package com.zht.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zht.dao.SolutionDao;
import com.zht.entity.Solution;
import com.zht.mapper.SolutionMapper;

@Repository
public class SolutionImpl implements SolutionDao {

	@Autowired
	private SqlSession sqlSession;

	public Integer add(Solution solution) {
		return sqlSession.getMapper(SolutionMapper.class).insert(solution);
	}

	@Override
	public List<Solution> queryAll() {
		return sqlSession.getMapper(SolutionMapper.class).queryAll();
	}
	
	@Override
	public Solution queryById(Long sid) {
		return sqlSession.getMapper(SolutionMapper.class).queryById(sid);
	}

	@Override
	public Integer putById(Solution solution) {
		return sqlSession.getMapper(SolutionMapper.class).putSolution(solution);
	}

}
