package com.movie.main.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.movie.code.service.ICommonCodeService;
import com.movie.code.vo.CodeVO;
import com.movie.common.util.ImageUtils;
import com.movie.main.service.IMainService;
import com.movie.main.vo.ActorSearchVO;
import com.movie.main.vo.ActorVO;
import com.movie.main.vo.CompanySearchVO;
import com.movie.main.vo.CompanyVO;
import com.movie.main.vo.DirectorSearchVO;
import com.movie.main.vo.DirectorVO;
import com.movie.main.vo.MovieSearchVO;
import com.movie.main.vo.MovieVO;

@Controller
public class MainController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ServletContext application;
	
	@Autowired
	private IMainService mainService;
	
	@Autowired
	private ICommonCodeService codeService;
	
	@ModelAttribute("cateList")
	public List<CodeVO> categoryData() {
		logger.debug("codeList 처리");
		List<CodeVO> codeList = codeService.getCodeListByParent("GR00");
		return codeList;
	}
	
	@RequestMapping("/")
	public String movieList(@ModelAttribute("searchVO")MovieSearchVO searchVO,Model model)throws Exception {
		logger.debug("movieList 메서드 진입");
		searchVO.setRowSizePerPage(6);
		searchVO.setSearchCategory("GR07");
		List<MovieVO> comedeList = mainService.getMovieList(searchVO);
		
		searchVO.setSearchCategory("GR17");
		List<MovieVO> actionList = mainService.getMovieList(searchVO);
		
		searchVO.setSearchCategory("GR14");
		List<MovieVO> romanList = mainService.getMovieList(searchVO);
		

		model.addAttribute("comedeList",comedeList);
		model.addAttribute("actionList",actionList);
		model.addAttribute("romanList",romanList);
		
		return "main/mainList";
	}
	
	@RequestMapping("/main/mainSearchList.wow")
	public String getMovieSearchList(@ModelAttribute("searchVO")MovieSearchVO searchVO,Model model)throws Exception {
		searchVO.setRowSizePerPage(20);
		List<MovieVO> movieList = mainService.getMovieSearchList(searchVO);

		model.addAttribute("movieList",movieList);
		return "main/mainSearchList";
	}
	
	
	
	@RequestMapping("/main/mainView.wow")
	public String movieView(String movieCd,Model model)throws Exception {
		
		Map<String, Object> retMap =  mainService.getMovie(movieCd);
		MovieVO movie = (MovieVO) retMap.get("movie");
		List<MovieVO>  movieList =	mainService.getSimilarMovieList(movie.getMovieNm());
		//과정4. 모델로부터 전달받은 결과물을 속성에 저장
//		model.addAttribute("movie", movie);
		model.addAllAttributes(retMap);
		model.addAttribute("movieList", movieList);
		System.out.println(movieList);
		return "main/mainView";
	}

	@RequestMapping("/main/mainForm.wow")
	public String movieForm()throws Exception {
		return "main/mainForm";
	}
	
	@RequestMapping("/main/mainRegist.wow")
	public String movieRegist(@RequestParam(name="moviePoster", required=false) MultipartFile movieImg,
			MovieVO movie, @RequestParam(name = "actorCd", required = false) String[] actorCds,
							@RequestParam(name = "peopleCd", required = false) String[] directorCds,
							@RequestParam(name = "companyCd", required = false) String[] companyCds,
							@RequestParam(name = "commCd", required = false) String[] genreCds
							)throws Exception {
		
		logger.debug("movie={}", movie);
		logger.debug("movieImg={}", ToStringBuilder.reflectionToString(movieImg));
//		logger.debug("actorCds={}", actorCds);
//		logger.debug("directorCds={}", directorCds);
//		logger.debug("companyCds={}", companyCds);
//		logger.debug("genreCds={}", genreCds);
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		retMap.put("movie", movie);
		retMap.put("actors", actorCds);
		retMap.put("directors", directorCds);
		retMap.put("companys", companyCds);
		retMap.put("genres", genreCds);
		
		String movieCd = mainService.getNewMovieCd();
		movie.setMovieCd(movieCd);
		
		if(movieImg != null && !movieImg.isEmpty()) {
			String imgType = ".jpg";
			if( movieImg.getContentType().contains("png")) {	// image/jpeg image/png""
				 imgType = ".png";
			}
			String imgName = movieCd + imgType;
			// 이미지 정보 저장
//			logger.debug("imgName = {}, path2={}" , imgName,  path2);
			// 개발시 프로젝트 폴더에  
//			String filePath = "/home/pc16/web-workspace/moviereco/src/main/webapp/images/movie";
			// 실제 서버에 
			String filePath = application.getRealPath("/images/movie");
			FileUtils.copyInputStreamToFile(movieImg.getInputStream(), new File(filePath, imgName));
			movie.setMovieImg(imgName);
			//		썸네일 이미지 생성 
			ImageUtils.thumbNailCreate(filePath , imgName);
		}
		mainService.registMovie(retMap);
		
		return "redirect:/";
	}
	
	
	
	
	
	@RequestMapping("/main/mainEdit.wow")
	public String movieEdit(@RequestParam(name = "movieCd") String movieCd,Model model)throws Exception {
		
		Map<String, Object> retMap =  mainService.getMovie(movieCd);
		model.addAllAttributes(retMap);
		
		return "main/mainEdit";
	}
	
	@RequestMapping(value = "/main/mainModify.wow", method = RequestMethod.POST)
	public String movieModify(@RequestParam(name="moviePoster", required=false) MultipartFile moviePoster,
							MovieVO movie
//							@RequestParam(name = "actorCd", required = false) String[] actorCds,
//							@RequestParam(name = "peopleCd", required = false) String[] directorCds,
//							@RequestParam(name = "companyCd", required = false) String[] companyCds,
//							@RequestParam(name = "commCd", required = false) String[] genreCds
							)throws Exception {
		
		logger.debug("movie={}", movie);
//		logger.debug("movieImg={}", ToStringBuilder.reflectionToString(movieImg));
//		logger.debug("actorCds={}", actorCds);
//		logger.debug("directorCds={}", directorCds);
//		logger.debug("companyCds={}", companyCds);
//		logger.debug("genreCds={}", genreCds);
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		retMap.put("movie", movie);
//		retMap.put("actors", actorCds);
//		retMap.put("companys", companyCds);
//		retMap.put("directors", directorCds);
//		retMap.put("genres", genreCds);
		
//		String movieCd = mainService.getNewMovieCd();
//		movie.setMovieCd(movieCd);
		/*
		if(movieImg != null && !movieImg.isEmpty()) {
			String imgType = ".jpg";
			if( movieImg.getContentType().contains("png")) {	// image/jpeg image/png""
				 imgType = ".png";
			}
			String imgName = movieCd + imgType;
			// 이미지 정보 저장
			String path2 = application.getRealPath("/imag/movie");
			logger.debug("imgName = {}, path2={}" , imgName,  path2);
			String filePath = "/home/pc-15/web-workspace/moviereco/src/main/webapp/images/movie";
			FileUtils.copyInputStreamToFile(movieImg.getInputStream(), new File(filePath, imgName));
			movie.setMovieImg(imgName);
			//		썸네일 이미지 생성 
			ImageUtils.thumbNailCreate(filePath , imgName);
		}
		*/
		mainService.updateMovie(retMap);
		
		return "redirect:/";
//		return "main/mainSearchList";
	}
	
	
	
	
	@GetMapping("/main/getActorList")
	@ResponseBody
	public Map<String, Object> actorRegist(ActorSearchVO searchVO)
			throws Exception {
		List<ActorVO> list = mainService.getActorList(searchVO);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", true);
		map.put("data", list);
		map.put("paging", searchVO);
		return map;
	}
	@GetMapping("/main/getDirectorList")
	@ResponseBody
	public Map<String, Object> directorRegist(DirectorSearchVO searchVO)
			throws Exception {
		List<DirectorVO> list = mainService.getDirectorList(searchVO);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", true);
		map.put("data", list);
		map.put("paging", searchVO);
		return map;
	}
	@GetMapping("/main/getCompanyList")
	@ResponseBody
	public Map<String, Object> companyRegist(CompanySearchVO searchVO)
			throws Exception {
		List<CompanyVO> list = mainService.getCompanyList(searchVO);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", true);
		map.put("data", list);
		map.put("paging", searchVO);
		return map;
	}

}
