package com.movie.member.web;

import javax.inject.Inject;import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

//import com.movie.code.service.ICommonCodeService;
import com.movie.common.vo.ResultMessageVO;
import com.movie.exception.BizException;
import com.movie.member.service.MemberService;
import com.movie.member.vo.MemberJoinVO;
import com.movie.member.vo.MemberVO;


//@SessionAttributes
// UI/ 기능적으로 한번에 처리를 못하고 여러화면으로 분리해서
// 작업하실때 세션을 활용해서 최종단계에서 처리 할 때 사용
@Controller
@SessionAttributes(names = "memberJoin")
public class MemberJoinController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private MemberService memberService;	
	
//	@Inject
//	private ICommonCodeService codeService;

	// @ModelAttribute
	// 공통(반복)적으로 사용되는 모델객체를 저장할 때 사용 
	// 해당 컨트롤러의 모든 요청 전에 호출되어 저장됩니다.
	// * 만약 동일한 이름의 모델이 존재한다면 처리하지 않습니다.
	// @SessionAttributes 사용시 
	// 컨트롤러 메서드에서 해당 객체 사용시 이미 모델에 저장이 되어있어야 합니다.
	@ModelAttribute("memberJoin")
	public MemberJoinVO getInitJoinVO() {
		System.out.println("------------------------------");
		System.out.println(" memberJoin 생성 ");
		System.out.println("------------------------------");
		MemberJoinVO joinVO = new MemberJoinVO();
		return joinVO;
	}
	
	/**
	 * <b>가입화면 1단계</b><br>
	 * 회원가입 동의 화면
	 * <ul>
	 * <li>이용약관 동의
	 * <li>개인정보 수집동의
	 * <li>이벤트/ 프로모션 
	 * </ul>
	 * 
	 * @return String 뷰이름
	 * @throws Exception
	 */
	@RequestMapping(value = {"/join/step1", "/join/join"})
	public String joinGet(ModelMap model) throws Exception {
		logger.debug("step1 ");
		// 아래처럼 기술하면 세션에 있는 기존 'MemberJoin' 정보가 사라져요~
		// model.addAttribute("memberJoin", new MemberJoinVO());
		String view = "join/step1";
		return view;
	}

	/**
	 * <b>가입화면 2단계</b><br>
	 * 주요 필수정보 입력단계
	 * <p>
	 * 아이디, 비밀번호, 이름, 이메일 등
	 * </p>
	 * 
	 * @return String 뷰이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/join/step2")
	public String joinPost(@ModelAttribute("memberJoin") MemberJoinVO joinVO, BindingResult errors) throws Exception {
		logger.debug("step2 - joinVO = {}", joinVO);
		String view = "join/step2";
		
		// 1단계의 정보가 없으면 1단계 화면으로 리턴
		if(isValidStep1(joinVO, errors) == false) {
			return "join/step1";
		}

		return view;
	}

	/**
	 * <b>가입화면 3단계</b><br>
	 * 사용자 필수 및 선택정보 입력단계
	 * <p>
	 * 필수 : 주민번호, 우편번호, 주소
	 * </p>
	 * <p>
	 * 선택 : HP, 직업, 취미 등
	 * </p>
	 * 
	 * @return String 뷰이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/join/step3")
	public String option(@ModelAttribute("memberJoin") MemberJoinVO joinVO, BindingResult errors) throws Exception {
		String view = "join/step3";
		logger.debug("step3	{}", joinVO);
		// 1단계의 정보가 없으면 1단계 화면으로 리턴
		if(isValidStep1(joinVO, errors) == false ) {
			return "join/step1";
		}
		
		// 2단계의 필수 정보가 없으면 2단계 화면으로 리턴
		if(isValidStep2(joinVO, errors) == false ) {
			return "join/step2";
		}
		
		return view;
	}

	/**
	 * <b>가입화면 최종 단계</b><br>
	 * 사용자가 입력한 정보의 결과를 보여준다.
	 * 
	 * @return String 뷰이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/join/regist")
	public String regist(@ModelAttribute("memberJoin") MemberJoinVO joinVO, BindingResult errors, Model model, SessionStatus sessionStatus)
			throws Exception {
		String view = "join/regist";
		logger.debug("step4	{}", joinVO);
		
		// 1단계의 정보가 없으면 1단계 화면으로 리턴
		if(isValidStep1(joinVO, errors) == false) {
			return "join/step1";
		}
		
		// 2단계의 필수 정보가 없으면 2단계 화면으로 리턴
		if(isValidStep2(joinVO, errors) == false) {
			return "join/step2";
		}
		
		// 3단계의 필수 정보가 없으면 3단계 화면으로 리턴
		if(isValidStep3(joinVO, errors) == false) {
			return "join/step3";
		}
		
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			memberService.registMember(joinVO);
			messageVO.setResult(true)
						   .setTitle("회원가입	성공")
						   .setMessage("회원가입을	완료되었습니다.	즐거운	하루	되세요")
						   .setUrlTitle("로그인")
						   .setUrl("/login/login.wow");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			messageVO.setResult(false)
			         .setTitle("회원가입	실패")
							 .setMessage("회원가입에	오류가	발생했습니다. 지속적으로	실패를	하시면	관리자에게 문의하시기 바랍니다.")
							 .setUrlTitle("문의하기")
							 .setUrl("/question/regist"); // 가상
		}
		// * 세션 자원 정리
		sessionStatus.setComplete(); 
		model.addAttribute("resultMessage", messageVO);
		return view;
	}

	/**
	 * <b>가입취소</b><br>
	 * 가입 중 입력한 정보 제거 후 메인화면으로 이동
	 * 
	 * @return String 뷰이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/join/cancel")
	public String cancel(SessionStatus sessionStatus) throws Exception {
		String view = "redirect:/index.jsp";
		// 세션 자원 정리
		sessionStatus.setComplete(); 
		return view;
	}

	private boolean isValidStep1(MemberJoinVO joinVO, BindingResult errors) {
		// 1단계의 필수 정보 체크
		// * 이용약관, 개인정보 수집
		if (!"Y".equals(joinVO.getAgreeYn())) {
			errors.rejectValue("agreeYn", "errors.required", "이용약관 동의는 필수입니다.");
		}
		if (!"Y".equals(joinVO.getPrivacyYn())) {
			errors.rejectValue("privacyYn", "errors.required", "개인정보수집 및 이용에 대한 동의는 필수입니다.");
		}			
			
		return !errors.hasErrors();
	}
	
	private boolean isValidStep2(MemberJoinVO joinVO, BindingResult errors) {
		// 2단계의 필수 정보 체크
		// * 아이디, 비밀번호, 이름, 이메일 등
		if (StringUtils.isBlank(joinVO.getMemId())) {
			errors.rejectValue("memId", "errors.required", "회원아이디는 필수입니다.");
		}
		// 아이디를 사용중인지 확인
		try {
			MemberVO vo = memberService.getMember(joinVO.getMemId());
			if (vo != null) {
			errors.rejectValue("memId", "errors.required", "이미 사용하고 있는 아이디입니다.");
			}
		}catch(BizException e){
			logger.error(e.getMessage(), e);
		}
		
		if (StringUtils.isBlank(joinVO.getMemPass())) {
			errors.rejectValue("memPass", "errors.required", "패스워드는 필수입니다.");
		}

		if (StringUtils.isBlank(joinVO.getMemName())) {
			errors.rejectValue("memName", "errors.required", "이름은 필수입니다.");
		}
		
		// 패스워드, 패스워드 확인 체크
		if (joinVO.getMemPass() != null && !joinVO.getMemPass().equals(joinVO.getMemPassConfirm()) ) {
			errors.reject("errors.notmatched", "비밀번호가 틀립니다.");
		}
		
		return !errors.hasErrors();
	}

	private boolean isValidStep3(MemberJoinVO joinVO, BindingResult errors) {
		// 3단계의 필수 정보 체크 
		// * 우편번호, 주소
		if (StringUtils.isBlank(joinVO.getMemZip())) {
			errors.rejectValue("memZip", "errors.required", "우편번호는 필수입니다.");
		}

		if (StringUtils.isBlank(joinVO.getMemAdd1())) {
			errors.rejectValue("memAdd1", "errors.required", "기본주소는 필수입니다.");
		}

		if (StringUtils.isBlank(joinVO.getMemAdd2())) {
			errors.rejectValue("memAdd2", "errors.required", "상세주소는 필수입니다.");
		}
		if(errors.hasFieldErrors()) {
			errors.reject("errors.required", "일부 필수 항목에 값이 비었습니다.");
		}
		return !errors.hasErrors();
	}

} // class








