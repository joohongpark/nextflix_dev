package com.movie.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.movie.main.vo.ActorVO;
import com.movie.main.vo.CompanyVO;
import com.movie.main.vo.DirectorVO;
import com.movie.main.vo.GenreVO;
import com.movie.main.vo.MovieSearchVO;
import com.movie.main.vo.MovieVO;

@Mapper
public interface IMainDao {
	
	public int getMovieCount(MovieSearchVO searchVO);
	
	public List<MovieVO> getMovieList(MovieSearchVO searchVO);
	
	public MovieVO getMovie(String movieCd);
	public List<ActorVO> getActorListByMovieCd(String movieCd);
	public List<DirectorVO> getDirectorListByMovieCd(String movieCd);
	public List<CompanyVO> getCompanyListByMovieCd(String movieCd);
	public List<GenreVO> getGenreListByMovieCd(String movieCd);


	
	public int insertMovie(MovieVO movie);
	public int insertMovieActor(ActorVO actor);
	public int insertMovieDirector(DirectorVO director);
	public int insertMovieCompany(CompanyVO company);
	public int insertGenre(GenreVO genre);
	
	public int updateMovie(MovieVO movie);

	public String selectNewMovieCode();
	/** content based filtering 유사 영화 10건 조회*/  
	public List<MovieVO> getSimilarMovieList(Map<String, Object> movieList);
	
}
