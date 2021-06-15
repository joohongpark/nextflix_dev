package com.movie.keep.service;

import java.util.List;

import com.movie.exception.BizException;
import com.movie.keep.vo.KeepSearchVO;
import com.movie.keep.vo.KeepVO;

public interface IKeepService {

	/**
	 * 찜목록 조회 <br>
	 */
	public List<KeepVO> getKeepList(KeepSearchVO searchVO) throws BizException;

	/** 찜등록 */
	public void registKeep(KeepVO keep) throws BizException;

	/**
	 * 찜 삭제 <br>
	 * 찜목록이 존재하지 않으면 BizNotFoundException
	 */
	public void removeKeep(KeepVO keep) throws BizException;


}