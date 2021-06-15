package com.movie.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.movie.exception.BizDuplicateException;
import com.movie.exception.BizNotEffectedException;
import com.movie.exception.BizNotFoundException;
import com.movie.member.dao.MemberDao;
import com.movie.member.vo.MemberSearchVO;
import com.movie.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDao memberDao;

	@Override
	public void registMember(MemberVO member) {
		try {
			int cnt = memberDao.insertMember(member);
			if(cnt < 1) {
				throw new BizNotEffectedException("회원등록이 되지 않았습니다.", member);
			}
		} catch(DuplicateKeyException e) {			
			throw new BizDuplicateException(e.getMessage() , e);
		}
	}

	@Override
	public void modifyMember(MemberVO member) {
			int cnt = memberDao.updateMember(member);
			if(cnt < 1) {
				throw new BizNotEffectedException("회원정보 수정이 되지 않았습니다.", member);
			}
	}

	@Override
	public void removeMember(MemberVO member) {
			int cnt = memberDao.deleteMember(member);
	}

	@Override
	public MemberVO getMember(String memId) {
			MemberVO member = memberDao.getMember(memId);		
			if(member == null) {
				throw new BizNotFoundException("회원 [" + memId + "] 조회 실패");
			}
			return member;
	}

	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
			int cnt = memberDao.getMemberCount(searchVO);
			searchVO.setTotalRowCount(cnt);
		
			searchVO.setting();
			List<MemberVO> list = memberDao.getMemberList(searchVO);
			return list;
	}

} // class
