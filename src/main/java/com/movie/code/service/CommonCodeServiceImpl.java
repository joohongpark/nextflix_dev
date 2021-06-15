package com.movie.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.code.dao.ICommonCodeDao;
import com.movie.code.vo.CodeVO;
import com.movie.exception.BizException;

@Service
public class CommonCodeServiceImpl implements ICommonCodeService{
	
	@Autowired
	private ICommonCodeDao codeDao;
	
	@Override
	public List<CodeVO> getCodeListByParent(String code) throws BizException {
	
			return codeDao.getCodeListByParent(code);
	}

}







