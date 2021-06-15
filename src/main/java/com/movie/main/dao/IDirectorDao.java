package com.movie.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.movie.main.vo.DirectorSearchVO;
import com.movie.main.vo.DirectorVO;

@Mapper
public interface IDirectorDao {
	
	public int getDirectorCount(DirectorSearchVO searchVO);
	
	public List<DirectorVO> getDirectorList(DirectorSearchVO searchVO);
}
