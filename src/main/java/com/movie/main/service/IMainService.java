package com.movie.main.service;

import java.util.List;
import java.util.Map;

import com.movie.exception.BizException;
import com.movie.main.vo.ActorSearchVO;
import com.movie.main.vo.ActorVO;
import com.movie.main.vo.CompanySearchVO;
import com.movie.main.vo.CompanyVO;
import com.movie.main.vo.DirectorSearchVO;
import com.movie.main.vo.DirectorVO;
import com.movie.main.vo.MovieSearchVO;
import com.movie.main.vo.MovieVO;

public interface IMainService {
	
	public List<MovieVO> getMovieList(MovieSearchVO searchVO) throws BizException;

	public Map<String, Object> getMovie(String movieCd) throws BizException;

	public List<MovieVO> getMovieSearchList(MovieSearchVO searchVO) throws BizException;

	
	public List<ActorVO> getActorList(ActorSearchVO searchVO) throws BizException;
	
	public List<DirectorVO> getDirectorList(DirectorSearchVO searchVO) throws BizException;
	
	public List<CompanyVO> getCompanyList(CompanySearchVO searchVO) throws BizException;
	
	public void registMovie(Map<String, Object> retMap) throws BizException;
	
	public void updateMovie(Map<String, Object> retMap) throws BizException;
	
	public String getNewMovieCd() throws BizException;
    /** content based filtering 유사 영화 추천 ( 평점정보와 장르를 가지고) top 10*/  
	public List<MovieVO> getSimilarMovieList(String movieNm) throws BizException;
}
