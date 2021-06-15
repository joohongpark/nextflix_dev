package com.movie.member.web;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.code.service.ICommonCodeService;
import com.movie.common.vo.ResultMessageVO;
import com.movie.exception.BizDuplicateException;
import com.movie.exception.BizNotFoundException;
import com.movie.exception.BizPasswordNotMatchedException;
import com.movie.member.service.MemberService;
import com.movie.member.vo.MemberSearchVO;
import com.movie.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Inject
	private MemberService memberService;
	
	@Inject
	private ICommonCodeService codeService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	@RequestMapping("/memberList.wow")
	public String memberList(@ModelAttribute("searchVO") MemberSearchVO searchVO 
			                    , Model model) throws Exception {
		logger.debug("searchVO={}", searchVO);		
		
		List<MemberVO> list = memberService.getMemberList(searchVO);
		logger.debug("list={}", list);
		
		model.addAttribute("memberList", list);		
		
		return "member/memberList";
	}
	@RequestMapping("/memberView.wow")
	public String memberView(@RequestParam("memId") String memId, Model model) throws Exception {
		logger.debug("memId={}", memId);
		try {
			MemberVO member =  memberService.getMember(memId);
			model.addAttribute("member", member);
			
			return "member/memberView";
			
		} catch (BizNotFoundException e) {
			logger.error(e.getMessage(), e);
			ResultMessageVO vo = new ResultMessageVO();
			vo.setResult(false)
			  .setTitle("회원 조회 실패")
			  .setMessage("해당 회원이 존재하지않거나, 삭제되었습니다.")
			  .setUrl("memberList.wow")
			  .setUrlTitle("목록으로");
			model.addAttribute("messageVO", vo);
			
			return "common/message";
		}
	} 
	@RequestMapping(value = "/memberMypage.wow", method = RequestMethod.GET)
	public String memberMypage(@RequestParam("memId") String memId, Model model) throws Exception {
		logger.debug("memId={}", memId);
		try {
			MemberVO member =  memberService.getMember(memId);
			model.addAttribute("member", member);
			
			return "member/memberMypage";
			
		} catch (BizNotFoundException e) {
			logger.error(e.getMessage(), e);
			ResultMessageVO vo = new ResultMessageVO();
			vo.setResult(false)
			  .setTitle("회원 조회 실패")
			  .setMessage("해당 회원이 존재하지않거나, 삭제되었습니다.")
			  .setUrl("memberList.wow")
			  .setUrlTitle("목록으로");
			model.addAttribute("messageVO", vo);
			
			return "common/message";
		}
	} 
	@RequestMapping(value = "/memberEdit.wow", method = RequestMethod.GET)
	public String memberEdit(String memId, Model model) throws Exception {
		logger.debug("memId = {}", memId);
		try {
			MemberVO member = memberService.getMember(memId);
			model.addAttribute("member", member);
			return "member/memberEdit";

		} catch (BizNotFoundException e) {
			logger.warn(e.getMessage(), e);
			ResultMessageVO vo = new ResultMessageVO();
			vo.setResult(false).setTitle("회원 조회 실패").setMessage("해당 회원은 삭제되었습니다.").setUrl("memberList.wow")
					.setUrlTitle("목록으로");
			model.addAttribute("messageVO", vo);
			return "common/message";
		}
	}
	
	@RequestMapping(value = "/memberModify.wow", method = RequestMethod.POST)
	public String memberModify(@ModelAttribute("member") @Valid MemberVO membermember, BindingResult errors,
			HttpServletRequest req, Model model) throws Exception {
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			// 검증
			if (errors.hasErrors()) {
			return "member/memberEdit";
			}
			
			memberService.modifyMember(membermember);
			messageVO.setResult(true).setTitle("회원 수정 성공").setMessage("회원의 정보를 수정하였습니다.").setUrl("memberList.wow")
					.setUrlTitle("목록으로");
			
		} catch (BizNotFoundException e) {
			e.printStackTrace();
			ResultMessageVO vo = new ResultMessageVO();
			vo.setResult(false)
			  .setTitle("조회 실패")
			  .setMessage("해당 회원이 존재하지않거나, 삭제되었습니다.")
			  .setUrl("memberList.wow")
			  .setUrlTitle("목록으로");
			}
			model.addAttribute("messageVO", messageVO);
			return "common/message";
			
		}
	
	@RequestMapping("/memberForm.wow")
	public String memberForm(Model model) throws Exception {
		model.addAttribute("member", new MemberVO());
		return "member/memberForm";
	}
	
	@RequestMapping(value = "/memberRegist.wow", method = RequestMethod.POST)
	public String memberRegist(@ModelAttribute("member") @Valid MemberVO member
			                       , BindingResult errors, Model model) throws Exception {
		ResultMessageVO messageVO = new ResultMessageVO();
		try {			
			if(errors.hasErrors()) {
				return "member/memberForm";
			}			
			memberService.registMember(member);
			messageVO.setResult(true).setTitle("회원 등록 성공").setMessage("해당 회원 등록에 성공했습니다.")
	             .setUrl("memberList.wow").setUrlTitle("목록으로");
		} catch (BizDuplicateException e) {
			logger.error(e.getMessage(), e);
			errors.reject("error.duplicate", "해당 아이디는 이미 사용중 입니다.");
			errors.rejectValue("memId", "error.duplicate", "해당 아이디는 이미 사용중 입니다.");
			return "member/memberForm"; 
		}
		model.addAttribute("messageVO", messageVO);
		return "common/message";
	}
	@RequestMapping(value = "/memberDelete.wow")
	public String memberDelete(MemberVO membermember, HttpServletRequest req, Model model) throws Exception {
		ResultMessageVO messageVO = new ResultMessageVO();
		try {

			memberService.removeMember(membermember);
			messageVO.setResult(true).setTitle("회원 삭제 성공").setMessage("해당 회원을 삭제했습니다.").setUrl("memberList.wow")
					.setUrlTitle("목록으로");
		} catch (BizNotFoundException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("회원 삭제 실패").setMessage("해당 회원이 존재하지 않습니다.").setUrl("memberList.wow")
					.setUrlTitle("목록으로");
		} catch (BizPasswordNotMatchedException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("회원 삭제 실패").setMessage("비밀번호를 확인하여 주세요");
		}
		model.addAttribute("messageVO", messageVO);
		return "common/message";
	}
	
}
