package com.movie.attach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.attach.dao.IAttachDao;
import com.movie.attach.vo.AttachVO;
import com.movie.exception.BizException;
import com.movie.exception.BizNotFoundException;

@Service
public class AttachServiceImpl implements IAttachService {
	@Autowired
	private IAttachDao attachDao;

	@Override
	public AttachVO getAttach(int atchNo) throws BizException {
		AttachVO vo = attachDao.getAttach(atchNo);
		if (vo == null) {
			throw new BizNotFoundException("첨부파일 [" + atchNo + "]에 대한 조회 실패");
		}
		return vo;
	}

	@Override
	public void increaseDownHit(int atchNo) throws BizException {
		attachDao.increaseDownHit(atchNo);
	}
}
