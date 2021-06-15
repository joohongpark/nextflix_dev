package com.movie.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.exception.BizException;
import com.movie.exception.BizNotFoundException;
import com.movie.exception.BizPasswordNotMatchedException;
import com.movie.login.vo.UserVO;
import com.movie.member.dao.MemberDao;
import com.movie.member.vo.MemberVO;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public UserVO loginCheck(UserVO user) throws BizException {
		
			MemberVO member = memberDao.getMember(user.getUserId());		
			if(member == null) {
				throw new BizNotFoundException("회원 [" + user.getUserId() + "] 조회 실패");
			}
			if( ! member.getMemPass().equals(user.getUserPass())) {
				throw new BizPasswordNotMatchedException("입력하시 패스워드가 올바르지 않습니다.");
			}
			UserVO vo = new UserVO();
			vo.setUserId(user.getUserId());
			vo.setUserPass(user.getUserPass());
			vo.setUserName(member.getMemName());
			vo.setUserRole(member.getMemAuth());
			return vo;	
	}

	@Override
	public void logout(UserVO user) throws BizException {
		// TODO 차후에 로그히스토리테이블이 생성되면..	
		
	}

}
