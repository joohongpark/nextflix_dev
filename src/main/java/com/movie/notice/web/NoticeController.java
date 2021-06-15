package com.movie.notice.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.movie.notice.service.INoticeService;
import com.movie.notice.vo.NoticeSearchVO;
import com.movie.notice.vo.NoticeVO;
import com.movie.attach.vo.AttachVO;
import com.movie.code.service.ICommonCodeService;
import com.movie.code.vo.CodeVO;
import com.movie.common.group.Regist;
import com.movie.common.util.MovieAttachUtils;
import com.movie.common.vo.ResultMessageVO;
import com.movie.exception.BizNotEffectedException;
import com.movie.exception.BizNotFoundException;
import com.movie.exception.BizPasswordNotMatchedException;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private INoticeService noticeService; 
	@Autowired
	private ICommonCodeService codeService;
	@Autowired
	private MovieAttachUtils attachUtils;

	// 컨트롤러에서 반복적으로 사용되는 참조데이터(코드성자료)
	// 요청 메서드전에 모델에 담아주는 어노테이션 @ModelAttribute
	@ModelAttribute("cateList")
	public List<CodeVO> categoryData() {
		logger.debug("codeList 처리");
		List<CodeVO> codeList = codeService.getCodeListByParent("BC00");
		return codeList;
	}

	// ModelAndView 에 모델정보와 뷰이름을 담고 리턴
	// 파라미터에 커멘드객체(VO)를 사용하면 요청파라미터 바인딩, 모델에 저장
	// 모델에 저장되는 이름이 클래스이름(첫글자 소문자)
	@RequestMapping("/noticeList.wow")
	public ModelAndView noticeList(@ModelAttribute("searchVO") NoticeSearchVO searchVO) throws Exception {
		logger.debug("noticeList 메서드 진입");
		logger.debug("searchVO = {}", searchVO);
		ModelAndView mav = new ModelAndView();
		List<NoticeVO> list = noticeService.getNoticeList(searchVO);

		// 과정 4. 모델로부터 전달받은 결과물을 속성에 저장
		mav.addObject("noticeList", list);
		mav.setViewName("notice/noticeList");
		return mav;
	}

	// 메서드 파라미터에 프리미터형 기술 자동으로 요청파라미터이름과 같다면 바인딩
	// 만약, 요청파라미터 이름 변수이름이 다르면 @RequestParam 사용
	@RequestMapping("/noticeView.wow")
	public String noticeView(@RequestParam("noNum") int noNum, Model model) throws Exception {
		NoticeVO notice = noticeService.getNotice(noNum);

		if (notice != null) {
			noticeService.increaseHit(noNum);
		}
		// 과정 4. 모델로부터 전달받은 결과물을 속성에 저장
		model.addAttribute("notice", notice);
		return "notice/noticeView";
	}

	@RequestMapping(value = "/noticeEdit.wow", method = RequestMethod.GET)
	public String noticeEdit(int noNum, Model model) throws Exception {
		logger.debug("noNum = {}", noNum);
		try {
			NoticeVO notice = noticeService.getNotice(noNum);
			// ** List<CodeVO> cateList = codeService.getCodeListByParent("BC00");
			// 과정 4. 모델로부터 전달받은 결과물을 속성에 저장
			model.addAttribute("notice", notice);
			// ** model.addAttribute("cateList", cateList);
			return "notice/noticeEdit";
		} catch (BizNotFoundException e) {
			logger.warn(e.getMessage(), e);
			ResultMessageVO vo = new ResultMessageVO();
			vo.setResult(false).setTitle("게시판 조회 실패").setMessage("해당 공지사항은 삭제되었습니다.").setUrl("noticeList.wow")
					.setUrlTitle("목록으로");
			model.addAttribute("messageVO", vo);
			return "common/message";
		}
	}

	@RequestMapping(value = "/noticeModify.wow", method = RequestMethod.POST)
	public String noticeModify(@ModelAttribute("notice") @Valid NoticeVO noticenotice, BindingResult errors,
			HttpServletRequest req, Model model) throws Exception {
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			// 검증
			if (errors.hasErrors()) {
				return "notice/noticeEdit";
			}

			noticenotice.setNoIp(req.getRemoteAddr());
			noticeService.modifyNotice(noticenotice);
			messageVO.setResult(true).setTitle("게시판 수정 성공").setMessage("게시물의 정보를 수정하였습니다.").setUrl("noticeList.wow")
					.setUrlTitle("목록으로");
		} catch (BizNotFoundException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("게시판 조회 실패").setMessage("해당 게시물이 존재하지 않습니다.").setUrl("noticeList.wow")
					.setUrlTitle("목록으로");
		} catch (BizPasswordNotMatchedException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("게시판 수정 실패").setMessage("비밀번호를 확인하여 주세요");
		} catch (BizNotEffectedException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("게시판 수정 실패").setMessage("해당 게시물의 정보를 변경하지 못했습니다.")
					.setUrl("noticeList.wow").setUrlTitle("목록으로");
		}
		model.addAttribute("messageVO", messageVO);
		return "common/message";
	}

	@RequestMapping("/noticeForm.wow")
	public String noticeForm(Model model) throws Exception {
		model.addAttribute("notice", new NoticeVO());
		return "notice/noticeForm";
	}

	@RequestMapping(value = "/noticeRegist.wow", method = RequestMethod.POST)
	public String noticeRegist(@RequestParam(name = "noFiles", required = false) MultipartFile[] noFiles,
			@ModelAttribute("notice") @Validated({ Default.class, Regist.class }) NoticeVO noticenotice,
			BindingResult errors, HttpServletRequest req) throws Exception {
		if (errors.hasErrors()) {
			return "notice/noticeForm";
		}

		// 업로드파일이 존재하는 경우 저장후 해당 정보를 vo에 설정
		if (noFiles != null) {
			logger.debug("--------------------------------");
			List<AttachVO> attaches = attachUtils.getAttachListByMultiparts(noFiles, "NOTICE", "notice");
			noticenotice.setAttaches(attaches);
			logger.debug("--------------------------------");
		}

		noticenotice.setNoIp(req.getRemoteAddr());
		noticeService.registNotice(noticenotice);
		return "redirect:noticeList.wow";
	}

	@RequestMapping(value = "/noticeDelete.wow")
	public String noticeDelete(NoticeVO noticenotice, HttpServletRequest req, Model model) throws Exception {
		ResultMessageVO messageVO = new ResultMessageVO();
		try {

			noticeService.removeNotice(noticenotice);
			messageVO.setResult(true).setTitle("게시판 삭제 성공").setMessage("해당 게시물을 삭제했습니다.").setUrl("noticeList.wow")
					.setUrlTitle("목록으로");
		} catch (BizNotFoundException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("게시판 삭제 실패").setMessage("해당 게시물이 존재하지 않습니다.").setUrl("noticeList.wow")
					.setUrlTitle("목록으로");
		} catch (BizPasswordNotMatchedException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("게시판 삭제 실패").setMessage("비밀번호를 확인하여 주세요");
		}
		model.addAttribute("messageVO", messageVO);
		return "common/message";
	}

	@RequestMapping("/test1.wow")
	public String test1(Model model) throws Exception {
		ResultMessageVO messageVO = new ResultMessageVO();
		noticeService.test1();
		messageVO.setResult(false).setTitle("게시판 트랜잭션 테스트1").setMessage("모든 정보가 성공적일때, DB 확인 요망 ");
		model.addAttribute("messageVO", messageVO);
		return "common/message";
	}

	@RequestMapping("/test2.wow")
	public String test2(Model model) throws Exception {
		ResultMessageVO messageVO = new ResultMessageVO();
		noticeService.test2();
		messageVO.setResult(false).setTitle("게시판 트랜잭션 테스트2").setMessage("일부 정보가 실패일때(Checked Exception), DB 확인 요망 ");
		model.addAttribute("messageVO", messageVO);
		return "common/message";
	}

	@RequestMapping("/test3.wow")
	public String test3(Model model) throws Exception {
		ResultMessageVO messageVO = new ResultMessageVO();
		noticeService.test3();
		messageVO.setResult(false).setTitle("게시판 트랜잭션 테스트3").setMessage("일부 정보가 실패일때(UnChecked Exception), DB 확인 요망 ");
		model.addAttribute("messageVO", messageVO);
		return "common/message";
	}
}// class
