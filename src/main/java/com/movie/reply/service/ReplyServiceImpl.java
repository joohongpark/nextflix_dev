package com.movie.reply.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.movie.exception.BizAccessFailException;
import com.movie.exception.BizException;
import com.movie.exception.BizNotFoundException;
import com.movie.reply.dao.IReplyDao;
import com.movie.reply.vo.ReplySearchVO;
import com.movie.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements IReplyService {
	
	@Resource
	private IReplyDao replyDao;
	
	@Override
	public List<ReplyVO> getReplyListByParent(ReplySearchVO searchVO) throws BizException {
		int rowCount = replyDao.getReplyCountByParent(searchVO);
		searchVO.setTotalRowCount(rowCount);
		searchVO.setting();
		return replyDao.getReplyListByParent(searchVO);
	}

	@Override
	public void registReply(ReplyVO reply) throws BizException {
		replyDao.insertReply(reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) throws BizException {
		ReplyVO vo = replyDao.getReply(reply.getReNo());
		if (vo == null) {
			throw new BizNotFoundException("글번호[" + reply.getReNo() + "]을 조회하지 못했습니다.");
		}
		/*
		 * if (!vo.getReMemId().equals(reply.getReMemId())) { throw new
		 * BizAccessFailException("해당 글의 작성자가 아닙니다."); }
		 */
		replyDao.updateReply(reply);
	}

	@Override
	public void removeReply(ReplyVO reply) throws BizException {		
		ReplyVO vo = replyDao.getReply(reply.getReNo());
		if (vo == null) {
			throw new BizNotFoundException("글번호[" + reply.getReNo() + "]가 존재하지 못했습니다.");
		}
		/*
		 * if (!vo.getReMemId().equals(reply.getReMemId())) { throw new
		 * BizAccessFailException("해당 글의 작성자가 아닙니다."); }
		 */
		replyDao.deleteReply(reply);
	}
}
