package com.movie.keep.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.movie.keep.vo.KeepSearchVO;
import com.movie.keep.vo.KeepVO;


@Mapper
public interface IKeepDao {
	
	public int getKeepCountByMember(KeepSearchVO searchVO);

	public List<KeepVO> getKeepListByMember(KeepSearchVO searchVO);

//	public KeepVO getKeep(String keMovieCd);

	public int insertKeep(KeepVO keep);

	public int deleteKeep(KeepVO keep);
}
