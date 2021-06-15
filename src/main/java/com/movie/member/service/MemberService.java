package com.movie.member.service;

import java.util.List;
import com.movie.member.vo.MemberSearchVO;
import com.movie.member.vo.MemberVO;

public interface MemberService {
	
	
	
	public void registMember(MemberVO member);

	public void modifyMember(MemberVO member);

	public void removeMember(MemberVO member);

	public MemberVO getMember(String memId);

	public List<MemberVO> getMemberList(MemberSearchVO searchVO);

}
