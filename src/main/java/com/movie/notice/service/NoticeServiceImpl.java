package com.movie.notice.service;

import java.util.List;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.attach.dao.IAttachDao;
import com.movie.attach.vo.AttachVO;
import com.movie.exception.BizException;
import com.movie.exception.BizNotEffectedException;
import com.movie.exception.BizNotFoundException;
import com.movie.exception.BizPasswordNotMatchedException;
import com.movie.notice.dao.INoticeDao;
import com.movie.notice.vo.NoticeSearchVO;
import com.movie.notice.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements INoticeService {

	@Autowired
	private INoticeDao NoticeDao;
	@Autowired
	private IAttachDao attachDao;

	@Override
	public List<NoticeVO> getNoticeList(NoticeSearchVO searchVO) throws BizException {
		// 페이징 계산
		int cnt = NoticeDao.getNoticeCount(searchVO);
		searchVO.setTotalRowCount(cnt);
		searchVO.setting();
		List<NoticeVO> list = NoticeDao.getNoticeList(searchVO);

//		// 목록의 첨부파일을 가져오기위해 개별적으로 쿼리 실행 
//		// n + 1 쿼리(한번의 쿼리에 하위쿼리 n번이 발생 ) 
//		if(list != null) {
//			for(NoticeVO vo : list) {
//				vo.setAttaches(attachDao.getAttachByParentNoList(vo.getBoNum()));
//			}
//		}

		return list;
	}

	@Override
	public NoticeVO getNotice(int boNum) throws BizException {
		NoticeVO Notice = NoticeDao.getNotice(boNum);
		if (Notice == null) {
			throw new BizNotFoundException("게시판[" + boNum + "] 조회 실패 ");
		}

		Notice.setAttaches(attachDao.getAttachByParentNoList(boNum));

		return Notice;
	}

	@Override
	public void registNotice(NoticeVO board) throws BizException {
		int cnt = NoticeDao.insertNotice(board);
		if (cnt < 1) {
			throw new BizNotEffectedException("게시판등록이 되지 않았습니다.", board);
		}

		// 첨부파일이 존재하는 경우 첨부파일 등록 , parentNo 설정 필요
		List<AttachVO> atchList = board.getAttaches();
		if (atchList != null) {
			for (AttachVO vo : atchList) {
				vo.setAtchParentNo(board.getNoNum()); // parentNo 설정 필요
				attachDao.insertAttach(vo);
			}
		}

	}

	@Override
	public void modifyNotice(NoticeVO board) throws BizException {
		// 패스워드 비교
		NoticeVO vo = NoticeDao.getNotice(board.getNoNum());
		if (vo == null) {
			throw new BizNotFoundException("글번호 [" + board.getNoNum() + "]이 존재하지 않습니다.");
		}
		if (!vo.getNoPass().equals(board.getNoPass())) {
			throw new BizPasswordNotMatchedException("패스워드가 일치하지 않습니다.");
		}
		int cnt = NoticeDao.updateNotice(board);
		if (cnt < 1) {
			throw new BizNotEffectedException("게시판 수정이 되지 않았습니다.", board);
		}
	}

	@Override
	public void removeNotice(NoticeVO board) throws BizException {
		// 패스워드 비교
		NoticeVO vo = NoticeDao.getNotice(board.getNoNum());
		if (vo == null) {
			throw new BizNotFoundException("글번호 [" + board.getNoNum() + "]이 존재하지 않습니다.");
		}
		if (!vo.getNoPass().equals(board.getNoPass())) {
			throw new BizPasswordNotMatchedException("패스워드가 일치하지 않습니다.");
		}
		int cnt = NoticeDao.deleteNotice(board);
		if (cnt < 1) {
			throw new BizNotEffectedException("게시물이 삭제되지 않았습니다.", board);
		}
	}

	@Override
	public void increaseHit(int boNum) throws BizException {
		NoticeDao.increaseHit(boNum);
	}

	private NoticeVO dummyCreate() {
		NoticeVO vo = new NoticeVO();
		vo.setNoCategory("BC01");
		vo.setNoTitle("트랜잭션 테스트를 하고 있습니다.");
		vo.setNoWriter("밀키스");
		vo.setNoPass("1004");
		vo.setNoContent("트랜잭션 테스트");
		vo.setNoIp("192.168.10.26");
		return vo;
	}

	@Override
	public void test1() throws Exception {
		// 2개의 쿼리가 모두 정상적인 경우
		NoticeVO board = dummyCreate();
		NoticeDao.insertNotice(board);
		NoticeDao.insertNotice(board);
	}

	@Override
	public void test2() throws Exception {
		// 2개의 쿼리 실행 후 CheckedException을 던졌을 때
		NoticeVO board = dummyCreate();
		NoticeDao.insertNotice(board);
		NoticeDao.insertNotice(board);
		throw new DataFormatException("날짜 형식이 올바르지 않습니다.");
	}

	@Override
	public void test3() throws Exception {
		// 2개의 쿼리 실행 후 UnCheckedException을 던졌을 때
		NoticeVO board = dummyCreate();
		NoticeDao.insertNotice(board);
		NoticeDao.insertNotice(board);
		throw new BizNotEffectedException("해당 정보를 등록하지 못했습니다.");
	}

}
