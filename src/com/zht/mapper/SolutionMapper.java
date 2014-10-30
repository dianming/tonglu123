package com.zht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zht.entity.Solution;

public interface SolutionMapper {

	@Insert("INSERT INTO solution(title,content,create_date) VALUES(#{title},#{content},#{createDate})")
	Integer insert(Solution solution);

	@Select("SELECT * FROM solution")
	public List<Solution> queryAll();

	@Select("SELECT * FROM solution WHERE id=#{sid}")
	public Solution queryById(@Param("sid") Long sid);

	@Update("UPDATE solution SET title=#{title},content=#{content},create_date=#{createDate},status=#{status} WHERE id=#{id}")
	public Integer putSolution(Solution solution);
}