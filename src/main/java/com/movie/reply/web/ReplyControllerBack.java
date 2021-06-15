package com.movie.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movie.exception.BizAccessFailException;
import com.movie.exception.BizNotFoundException;
import com.movie.login.vo.UserVO;
import com.movie.reply.service.IReplyService;
import com.movie.reply.vo.ReplySearchVO;
import com.movie.reply.vo.ReplyVO;

// @Controller
public class ReplyControllerBack {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private IReplyService replyService;
	
	// client  reNo=34&reContent=Hello  ->              
	// client  {"reNo": 34 , "reContent": "Hello"}  -> 
	
	@RequestMapping(value = "/reply/replyList", produces = "application/json;charset=utf-8" )
	@ResponseBody
	public Map<String, Object> replyList(ReplySearchVO searchVO) throws Exception {
		List<ReplyVO> list = replyService.getReplyListByParent(searchVO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("data", list);
		map.put("count", list.size());
		return map;
	}

	// @PostMapping ("/reply/replyRegist")
	@RequestMapping(value = "/reply/replyRegist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replyRegist(ReplyVO reply, HttpServletRequest req, HttpSession session) throws Exception {
		reply.setReIp(req.getRemoteAddr());
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId(user.getUserId());
		replyService.registReply(reply);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("msg", "정상 등록 되었습니다.");
		return map;
	}

	@RequestMapping(value = "/reply/replyModify")
	@ResponseBody
	public Map<String, Object> replyModify(ReplyVO reply, HttpSession session) throws Exception {
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId(user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			replyService.modifyReply(reply);
			map.put("result", true);
			map.put("msg", "수정 되었습니다.");
			return map;
		} catch (BizNotFoundException e) {
			map.put("result", false);
			map.put("msg", "글이 존재하지 않습니다.");
			return map;
		} catch (BizAccessFailException e) {
			map.put("result", false);
			map.put("msg", "접근에 실패했습니다.");
			return map;
		}
	}

}
