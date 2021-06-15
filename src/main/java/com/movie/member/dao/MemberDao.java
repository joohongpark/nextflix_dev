package com.movie.member.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.movie.member.vo.MemberVO;
import com.movie.member.vo.MemberSearchVO;

@Mapper
public interface MemberDao {

	public int selectMember(MemberVO member); // 회원목록을 조회한다

	public int insertMember(MemberVO member); // 회원목록을 저장한다

	public int updateMember(MemberVO member); // 회원목록을 업데이트한다

	public int deleteMember(MemberVO member); // 회원목록을 삭제한다

	public MemberVO getMember(String memId); 

	public List<MemberVO> getMemberList(MemberSearchVO searchVO);

	public int getMemberCount(MemberSearchVO searchVO);

}
