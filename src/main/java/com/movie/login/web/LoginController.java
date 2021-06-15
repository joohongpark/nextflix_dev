package com.movie.login.web;


import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.common.vo.ResultMessageVO;
import com.movie.exception.BizNotFoundException;
import com.movie.exception.BizPasswordNotMatchedException;
import com.movie.login.service.LoginService;
import com.movie.login.vo.UserVO;
import com.movie.member.vo.MemberVO;
import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LoginService loginService;

	// /login/login.wow GET -> 로그인 화면
	// /login/login.wow POST -> 로그인 처리
	// /login/logout.wow -> 로그아웃 처리
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	@RequestMapping(value = "/login/login.wow", method = RequestMethod.POST)
	public String loginCheck(UserVO user, HttpSession session, Model model) throws Exception {
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			UserVO vo = loginService.loginCheck(user);
			session.setAttribute("USER_INFO", vo);
			return "redirect:/";
		} catch (BizNotFoundException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("로그인 실패 ").setMessage("해당 회원이 존재하지 않습니다.").setUrl("/join/join.wow")
					.setUrlTitle("회원가입");
			model.addAttribute("messageVO", messageVO);
			return "common/message";
		} catch (BizPasswordNotMatchedException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("로그인 실패").setMessage("비밀번호가 올바르지 않습니다.").setUrl("/join/join.wow")
				  	 .setUrlTitle("비번조회");
			model.addAttribute("messageVO", messageVO);
			
			return "common/message";
		}
	}
	
	@RequestMapping(value="/login/login.wow", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) throws Exception {
		
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		System.out.println(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		
		System.out.println("네이버:" + naverAuthUrl);
		//네이버
		model.addAttribute("url", naverAuthUrl);

		return "/login/login";
	}
	
	 //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value ="/callback", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(@RequestParam String code, @RequestParam String state, Model model, MemberVO memberVO,HttpSession session  ) throws Exception, NullPointerException {
    	
        System.out.println("여기는 callback");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
 
        // 로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        System.out.println("result" + apiResult);
        
        //DB와 세션에 넣기
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(apiResult);
        JSONObject jsonObject = (JSONObject)jsonParser.parse(naverLoginBO.getUserProfile(oauthToken).toString());
        JSONObject response = (JSONObject)jsonObject.get("response");
//        System.out.println("이것은" + jsonObject.get("response"));
        logger.debug("response {}" , jsonObject.get("response"));
        logger.debug("obj {}" , obj);
        logger.debug("jsonObject {}" , jsonObject);
        
//        memberVO.setMemId((String)response.get("id"));
//        memberVO.setMemPass("1234"); //DB에서 Not null로 처리했기에 임의로 준 값
//        memberVO.setMemName((String) response.get("name"));
//        memberVO.setMemSex((String)response.get("sex"));
        
        
//        String name = (String)response.get("name"); 
        UserVO vo = new UserVO();
        vo.setUserId((String)response.get("id"));
        vo.setUserName((String) response.get("name"));
        vo.setUserRole("NAVER");
        logger.debug("User  {}" , vo);
		session.setAttribute("USER_INFO", vo);
        
        return "redirect:/";
    }
    	
    @RequestMapping("/login/logout.wow")
	public String logout(HttpSession session) throws Exception, ClassCastException {
		// 세션에서 USER_INFO 를 꺼내어
		UserVO vo = (UserVO) session.getAttribute("USER_INFO");
		if (vo != null) {
			loginService.logout(vo);
		}
		// 세션 무효화(파기)
		session.invalidate();
		
		return "redirect:/";
	}
//    //네이버 로그아웃
//    @RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
//    public String naverLogout(HttpSession session)throws IOException {
//    System.out.println("여기는 logout");
//    session.invalidate();
//    return "redirect:/";
//    }

}
