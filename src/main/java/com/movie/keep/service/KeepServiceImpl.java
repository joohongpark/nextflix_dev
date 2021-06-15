package com.movie.keep.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.movie.exception.BizDuplicateException;
import com.movie.exception.BizException;
import com.movie.exception.BizNotFoundException;
import com.movie.keep.dao.IKeepDao;
import com.movie.keep.vo.KeepSearchVO;
import com.movie.keep.vo.KeepVO;

@Service
public class KeepServiceImpl implements IKeepService {
	
	@Resource
	private IKeepDao keepDao;
	
	@Override
	public List<KeepVO> getKeepList(KeepSearchVO searchVO) throws BizException {
//	public List<KeepVO> getKeepList(KeepSearchVO searchVO, HttpSession session) throws BizException {
//		MemberVO member = (MemberVO) session.getAttribute("USER_INFO");
//		searchVO.setKeMemId(${sessionScope.USER_INFO.userName});
		
		int rowCount = keepDao.getKeepCountByMember(searchVO);
		searchVO.setTotalRowCount(rowCount);
		searchVO.setting();
		
		return keepDao.getKeepListByMember(searchVO);
	}

	@Override
	public void registKeep(KeepVO keep) throws BizException {
		try {
			keepDao.insertKeep(keep);
			
		} catch (DuplicateKeyException e) {
			throw new BizDuplicateException("이미 찜하신 영화입니다.");
		}
		
	}


	@Override
	public void removeKeep(KeepVO keep) throws BizException {
		keepDao.deleteKeep(keep);
	}




}
