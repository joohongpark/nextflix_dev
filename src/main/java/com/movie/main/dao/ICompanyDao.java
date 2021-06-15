package com.movie.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.movie.main.vo.CompanySearchVO;
import com.movie.main.vo.CompanyVO;

@Mapper
public interface ICompanyDao {
	
	public int getCompanyCount(CompanySearchVO searchVO);
	
	public List<CompanyVO> getCompanyList(CompanySearchVO searchVO);
}
