package com.movie.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.movie.main.vo.ActorSearchVO;
import com.movie.main.vo.ActorVO;

@Mapper
public interface IActorDao {
	
	public int getActorCount(ActorSearchVO searchVO);
	
	public List<ActorVO> getActorList(ActorSearchVO searchVO);
}
