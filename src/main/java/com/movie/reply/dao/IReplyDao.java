package com.movie.reply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.movie.reply.vo.ReplySearchVO;
import com.movie.reply.vo.ReplyVO;

@Mapper
public interface IReplyDao {
	public int getReplyCountByParent(ReplySearchVO searchVO);

	public List<ReplyVO> getReplyListByParent(ReplySearchVO searchVO);

	public ReplyVO getReply(int reNo);

	public int insertReply(ReplyVO reply);

	public int updateReply(ReplyVO reply);

	public int deleteReply(ReplyVO reply);
}
