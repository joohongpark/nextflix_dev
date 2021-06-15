package com.movie.attach.service;

import com.movie.attach.vo.AttachVO;
import com.movie.exception.BizException;

public interface IAttachService {
	/** 첨부파일 상세 조회 */
	public AttachVO getAttach(int atchNo) throws BizException;
	/** 다운로드 횟수 증가 */
	public void increaseDownHit(int atchNo) throws BizException;
}
