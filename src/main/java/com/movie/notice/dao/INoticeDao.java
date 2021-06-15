package com.movie.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.movie.exception.DaoException;
import com.movie.notice.vo.NoticeSearchVO;
import com.movie.notice.vo.NoticeVO;


// mybatis 가 자동으로 구현체를 생성해줘요 
@Mapper 
public interface INoticeDao {
	
	public int getNoticeCount(NoticeSearchVO searchVO) throws DaoException;
	
	/**
	 * <b>자유게시판 목록</b>을 반환한다.
	 * @param searchVO TODO
	 * @param Connection 
	 * @return 게시판목록 List&lt;NoticeVO&gt;
	 * @throws DaoException
	 */	
	public List<NoticeVO> getNoticeList(NoticeSearchVO searchVO) throws DaoException;	
	/**
	 * 글번호에 해당하는 게시물 반환 
	 * @param conn
	 * @param noNum 
	 * @return NoticeVO
	 * @throws DaoException
	 */
  public NoticeVO getNotice(int noNum) throws DaoException;
  
  public int insertNotice(NoticeVO notice) throws DaoException;
  
  public int updateNotice(NoticeVO notice) throws DaoException;	
  public int deleteNotice(NoticeVO notice) throws DaoException;
  
  /**
   * 해당 글번호의 조회수를 1 증가시킨다. 
   * @param conn
   * @param noNum
   * @return
   * @throws DaoException
   */
  public int increaseHit(int noNum) throws DaoException;
  
}
