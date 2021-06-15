package com.movie.keep.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.movie.code.service.ICommonCodeService;
import com.movie.code.vo.CodeVO;
import com.movie.common.vo.ResultMessageVO;
import com.movie.exception.BizDuplicateException;
import com.movie.exception.BizNotFoundException;
import com.movie.keep.service.IKeepService;
import com.movie.keep.vo.KeepSearchVO;
import com.movie.keep.vo.KeepVO;
import com.movie.login.vo.UserVO;

@Controller
public class KeepController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IKeepService keepService; 
	@Autowired
	private ICommonCodeService codeService;
	
	@ModelAttribute("genreList")
	public List<CodeVO> categoryData() {
		logger.debug("codeList 처리");
		List<CodeVO> codeList = codeService.getCodeListByParent("GR00");
		return codeList;
	}
	
	@RequestMapping("/keep/keepList.wow")
	public ModelAndView keep(@ModelAttribute("searchVO") KeepSearchVO searchVO, HttpSession session) throws Exception {
		UserVO vo = (UserVO)session.getAttribute("USER_INFO");
		searchVO.setKeMemId(vo.getUserId());
		logger.debug("keepList 메서드 진입");
		logger.debug("searchVO = {}", searchVO);
		ModelAndView mav = new ModelAndView();
		List<KeepVO> list = keepService.getKeepList(searchVO);
		
		Map<String, String> map = list.stream().collect(Collectors.groupingBy(KeepVO::getKeMovieCd, Collectors.mapping(KeepVO::getKeGenre, Collectors.joining(", #", "#", ""))));
		
		List<KeepVO> keepList = list.stream().distinct().map(v -> {v.setKeGenre(map.get(v.getKeMovieCd()));return v;}).collect(Collectors.toList());
		mav.addObject("keepList", keepList);
		mav.setViewName("keep/keepList");
		return mav;
	}
	
	@RequestMapping(value = "/keep/keepDelete.wow")
	public String keepDelete(KeepVO keep, HttpServletRequest req, Model model, HttpSession session) throws Exception {
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			UserVO vo = (UserVO)session.getAttribute("USER_INFO");
			keep.setKeMemId(vo.getUserId());
			
			keepService.removeKeep(keep);
			messageVO.setResult(true).setTitle("삭제 성공").setMessage("찜목록에서 삭제했습니다.").setUrl("keepList.wow")
					.setUrlTitle("목록으로");
		} catch (BizNotFoundException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("삭제 실패").setMessage("찜목록이 존재하지 않습니다.").setUrl("keepList.wow")
					.setUrlTitle("목록으로");
		}	model.addAttribute("messageVO", messageVO);
		return "common/message";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/keep/keepRegist.wow")
	public Map<String, Object> keepRegist(KeepVO keep, HttpServletRequest req, Model model, HttpSession session) throws Exception {
		logger.debug("keepRegist 메서드 진입");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserVO vo = (UserVO)session.getAttribute("USER_INFO");
			keep.setKeMemId(vo.getUserId());
			
			keepService.registKeep(keep);
			map.put("result", true);
			map.put("msg", "정상 등록 되었습니다.");
			
		} catch (BizDuplicateException e) {
			logger.error(e.getMessage(), e);
			map.put("result", false);
			map.put("msg", " 이미 등록 되었습니다.");
//			.setUrl("keepList.wow").setUrlTitle("목록으로");
		} 
		return map;
	}
}
