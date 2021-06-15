package com.movie.notice.service;

import java.util.List;

import com.movie.exception.BizException;
import com.movie.notice.vo.NoticeSearchVO;
import com.movie.notice.vo.NoticeVO;

public interface INoticeService {
  public List<NoticeVO> getNoticeList(NoticeSearchVO searchVO) throws BizException;	
  public NoticeVO getNotice(int boNum) throws BizException;	
  public void registNotice(NoticeVO board) throws BizException;	
  public void modifyNotice(NoticeVO board) throws BizException;	
  public void removeNotice(NoticeVO board) throws BizException;	
  public void increaseHit(int boNum) throws BizException;  
  
  //transaction test 를 위한 임시 메서드 
  public void test1() throws Exception;
  public void test2() throws Exception;
  public void test3() throws Exception;
}
