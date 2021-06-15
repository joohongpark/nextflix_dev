package com.movie.code.service;

import java.util.List;

import com.movie.code.vo.CodeVO;
import com.movie.exception.BizException;

public interface ICommonCodeService {
	
	List<CodeVO> getCodeListByParent(String code) throws BizException;
	
}
