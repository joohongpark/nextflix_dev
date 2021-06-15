package com.movie.notice.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.movie.attach.vo.AttachVO;
import com.movie.member.vo.MemberVO;


@SuppressWarnings("serial")
public class NoticeVO implements Serializable {
	
//	@Min(value=1, groups=Modify.class,  message = "글번호가 설정되지 않았습니다.")
	private int noNum;             /* 글번호 */
	
	@NotBlank( message = "글제목은 필수입니다.")
	@Size(min = 2, message = "글제목은 2글자 이상 입력하세요")
	private String noTitle;        /* 글제목 */
	private String noCategory;     /* 글분류 */
	
//	@NotNull(message = "작성자는 필수입니다.")
	@Pattern(regexp = "^[가-힣]+$", message = "한글만 사용가능합니다.")
	private String noWriter;       /* 작성자명 */
	
	@NotBlank(message = "패스워드는 필수입니다.")
	@Size(min = 4, max = 16, message = "패스워드는 4글자 이상 16글자 이하입니다.")
	private String noPass;         /* 비밀번호 */
	private String noContent;      /* 내용 */
	private String noIp;           /* 등록자 IP */
	private String noRegDate;      /* 등록일자 */
	private String noModDate;      /* 수정일자 */
	private String noDelYn;        /* 삭제 여부 */
	private String noCategoryNm;     /* 글분류명 */	
	private int noHit;          /* 조회수 */
	
	// ----------------------
	/** 첨부파일정보를 보관할 리스트
	 *  freeboard : attach = 1:N 관계 (collection)
	 *  */
	private List<AttachVO> attaches ;
	
		
	/** 수정 할 때  삭제할 대상 첨부파일 번호 */
	private int[] delAtchNos;
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public int getNoNum() {
		return noNum;
	}


	public void setNoNum(int noNum) {
		this.noNum = noNum;
	}


	public String getNoTitle() {
		return noTitle;
	}


	public void setNoTitle(String noTitle) {
		this.noTitle = noTitle;
	}


	public String getNoCategory() {
		return noCategory;
	}


	public void setNoCategory(String noCategory) {
		this.noCategory = noCategory;
	}


	public String getNoWriter() {
		return noWriter;
	}


	public void setNoWriter(String noWriter) {
		this.noWriter = noWriter;
	}


	public String getNoPass() {
		return noPass;
	}


	public void setNoPass(String noPass) {
		this.noPass = noPass;
	}


	public String getNoContent() {
		return noContent;
	}


	public void setNoContent(String noContent) {
		this.noContent = noContent;
	}


	public String getNoIp() {
		return noIp;
	}


	public void setNoIp(String noIp) {
		this.noIp = noIp;
	}


	public String getNoRegDate() {
		return noRegDate;
	}


	public void setNoRegDate(String noRegDate) {
		this.noRegDate = noRegDate;
	}


	public String getNoModDate() {
		return noModDate;
	}


	public void setNoModDate(String noModDate) {
		this.noModDate = noModDate;
	}


	public String getNoDelYn() {
		return noDelYn;
	}


	public void setNoDelYn(String noDelYn) {
		this.noDelYn = noDelYn;
	}


	public String getNoCategoryNm() {
		return noCategoryNm;
	}


	public void setNoCategoryNm(String noCategoryNm) {
		this.noCategoryNm = noCategoryNm;
	}


	public int getNoHit() {
		return noHit;
	}


	public void setNoHit(int noHit) {
		this.noHit = noHit;
	}


	public List<AttachVO> getAttaches() {
		return attaches;
	}


	public void setAttaches(List<AttachVO> attaches) {
		this.attaches = attaches;
	}


	public int[] getDelAtchNos() {
		return delAtchNos;
	}


	public void setDelAtchNos(int[] delAtchNos) {
		this.delAtchNos = delAtchNos;
	}

	
	

	
}
