package com.movie.main.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.exception.BizException;
import com.movie.exception.BizNotFoundException;
import com.movie.main.dao.IActorDao;
import com.movie.main.dao.ICompanyDao;
import com.movie.main.dao.IDirectorDao;
import com.movie.main.dao.IMainDao;
import com.movie.main.vo.ActorSearchVO;
import com.movie.main.vo.ActorVO;
import com.movie.main.vo.CompanySearchVO;
import com.movie.main.vo.CompanyVO;
import com.movie.main.vo.DirectorSearchVO;
import com.movie.main.vo.DirectorVO;
import com.movie.main.vo.GenreVO;
import com.movie.main.vo.MovieSearchVO;
import com.movie.main.vo.MovieVO;

@Service
public class MainServiceImpl implements IMainService {
	
	@Autowired
	private IMainDao mainDao;
		
	@Autowired
	private IActorDao actorDao;
	
	@Autowired
	private IDirectorDao directorDao;
	
	@Autowired
	private ICompanyDao companyDao;
	
	@Override
	public List<MovieVO> getMovieList(MovieSearchVO searchVO) throws BizException {
		searchVO.setting();
		List<MovieVO> list = mainDao.getMovieList(searchVO);
		
		return list;
	}
	@Override
	public List<MovieVO> getMovieSearchList(MovieSearchVO searchVO) throws BizException {
		int cnt = mainDao.getMovieCount(searchVO);
		searchVO.setTotalRowCount(cnt);
		searchVO.setting();
		List<MovieVO> list = mainDao.getMovieList(searchVO);
		
		return list;
	}

	@Override
	public Map<String, Object> getMovie(String movieCd) throws BizException {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		MovieVO movie = mainDao.getMovie(movieCd);
		if (movie == null) {
			throw new BizNotFoundException(movieCd + " 조회 실패");
		}
		
		List<ActorVO> actors = mainDao.getActorListByMovieCd(movieCd);
		List<DirectorVO> directors = mainDao.getDirectorListByMovieCd(movieCd);
		List<CompanyVO> companys = mainDao.getCompanyListByMovieCd(movieCd);
		List<GenreVO> genres = mainDao.getGenreListByMovieCd(movieCd);
		
		retMap.put("movie", movie);
		retMap.put("actors", actors);
		retMap.put("directors", directors);
		retMap.put("companys", companys);
		retMap.put("genres", genres);
		
		return retMap;
	}
	
	
	@Override
	public List<ActorVO> getActorList(ActorSearchVO searchVO) throws BizException {
		int cnt = actorDao.getActorCount(searchVO);
		searchVO.setTotalRowCount(cnt);
		searchVO.setting();
		return actorDao.getActorList(searchVO);
	}
	@Override
	public List<DirectorVO> getDirectorList(DirectorSearchVO searchVO) throws BizException {
		int cnt = directorDao.getDirectorCount(searchVO);
		searchVO.setTotalRowCount(cnt);
		searchVO.setting();
		return directorDao.getDirectorList(searchVO);
	}
	@Override
	public List<CompanyVO> getCompanyList(CompanySearchVO searchVO) throws BizException {
		int cnt = companyDao.getCompanyCount(searchVO);
		searchVO.setTotalRowCount(cnt);
		searchVO.setting();
		return companyDao.getCompanyList(searchVO);
	}
	@Override
	public void registMovie(Map<String, Object> retMap) throws BizException {
		// map에서 각 정보를 꺼내요 객체에 담기		
		MovieVO movie = (MovieVO)retMap.get("movie");
		String[] actors = (String[])retMap.get("actors");
		String[] directors = (String[])retMap.get("directors");
		String[] companys = (String[])retMap.get("companys");
		String[] genres = (String[])retMap.get("genres");
		
		// 각 정보에 대한 쿼리작업 
		mainDao.insertMovie(movie);
		
		if(actors != null && actors.length > 0) {
			for(String cd : actors) {
				ActorVO actor = new ActorVO();
				actor.setActorCd(cd);
				actor.setMovieCd(movie.getMovieCd());
				mainDao.insertMovieActor(actor);
			}
		}
		
		if(directors != null && directors.length > 0) {
			for(String cd : directors) {
				DirectorVO director = new DirectorVO();
				director.setPeopleCd(cd);
				director.setMovieCd(movie.getMovieCd());
				mainDao.insertMovieDirector(director);
			}
		}
		if(companys != null && companys.length > 0) {
			for(String cd : companys) {
				CompanyVO company = new CompanyVO();
				company.setCompanyCd(cd);
				company.setMovieCd(movie.getMovieCd());
				mainDao.insertMovieCompany(company);
			}
		}
		if(genres != null && genres.length > 0) {
			for(String cd : genres) {
				GenreVO genre = new GenreVO();
				genre.setGenreCd(cd);
				genre.setMovieCd(movie.getMovieCd());
				mainDao.insertGenre(genre);
			}
		}
		
		
	}
	
	@Override
	public void updateMovie(Map<String, Object> retMap) throws BizException {
		// map에서 각 정보를 꺼내요 객체에 담기		
		MovieVO movie = (MovieVO)retMap.get("movie");
//		String[] actors = (String[])retMap.get("actors");
//		String[] directors = (String[])retMap.get("directors");
//		String[] companys = (String[])retMap.get("companys");
//		String[] genres = (String[])retMap.get("genres");
		
		// 각 정보에 대한 쿼리작업 
		mainDao.updateMovie(movie);
//		
//		if(actors != null && actors.length > 0) {
//			for(String cd : actors) {
//				ActorVO actor = new ActorVO();
//				actor.setActorCd(cd);
//				actor.setMovieCd(movie.getMovieCd());
//				mainDao.insertMovieActor(actor);
//			}
//		}
//		
//		if(directors != null && directors.length > 0) {
//			for(String cd : directors) {
//				DirectorVO director = new DirectorVO();
//				director.setPeopleCd(cd);
//				director.setMovieCd(movie.getMovieCd());
//				mainDao.insertMovieDirector(director);
//			}
//		}
//		if(companys != null && companys.length > 0) {
//			for(String cd : companys) {
//				CompanyVO company = new CompanyVO();
//				company.setCompanyCd(cd);
//				company.setMovieCd(movie.getMovieCd());
//				mainDao.insertMovieCompany(company);
//			}
//		}
//		if(genres != null && genres.length > 0) {
//			for(String cd : genres) {
//				GenreVO genre = new GenreVO();
//				genre.setGenreCd(cd);
//				genre.setMovieCd(movie.getMovieCd());
//				mainDao.insertGenre(genre);
//			}
//		}
//		
		
	}
	
	@Override
	public String getNewMovieCd() throws BizException {
		
		return mainDao.selectNewMovieCode();
	}
	@Override
	public List<MovieVO> getSimilarMovieList(String movieNm) throws BizException {
		Map<String, Object> map =  similarMVs(movieNm);
		// 맵처 다시 
	    List<MovieVO> movieList =	mainDao.getSimilarMovieList(map);
		return movieList;
	}
	
	
	public Map<String, Object> similarMVs (String movieNM ){
		 Map<String, Object> param = new HashMap<String, Object>();
		 List<String> movieList = new ArrayList<String>();
		Runtime rt = Runtime.getRuntime();
		try {
			String h = System.getenv("HOME");
			Process proc = rt.exec("bash -i  " + h + "/web-workspace/moviereco/src/main/java/com/movie/main/util/start.sh " + movieNM);
			String s = null;
			String returnData = "";
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				while ((s = br.readLine()) != null) {
					if (s.contains("[") || s.contains("]")) {
						returnData += s;
					}
				}
				returnData = returnData.replace("[","");
				returnData = returnData.replace("]","");
				returnData = returnData.replaceAll(" ","");
				returnData = returnData.replaceAll("'","");
				returnData = returnData.trim();
				movieList = Arrays.asList(returnData.split(","));
				param.put("movieList", movieList);
				
			} catch (IOException ioe) {
				System.out.println("=========" + ioe.toString());
			}
		} catch (IOException e) {
			System.out.println("=========" + e.toString());
		}
		
		return param;
	}
	
}
