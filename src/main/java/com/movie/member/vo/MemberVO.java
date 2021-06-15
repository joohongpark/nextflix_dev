package com.movie.member.vo;

import java.io.Serializable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class MemberVO implements Serializable {

	@NotBlank(message = "회원아이디는 필수입니다.")
	@Size(min = 4, max = 16, message = "회원아이디는 4글자이상 16글자 이하입니다.")
	@Pattern(regexp = "^\\w+$", message = "회원아이디는 영문, 숫자 , 언더바(_)만 사용 가능합니다. ")

	private String memId; /* 회원아이디 */

	private String memPass; /* 회원비밀번호 */
	private String memName; /* 회원명 */
	private String memAuth; /* 인증 */
	private String memSex; /* 성별 */
	private String memBir; /* 회원생일 */
	private String memZip; /* 우편번호 */
	private String memAdd1; /* 기본주소 */
	private String memAdd2; /* 상세주소 */
	private String memHp; /* 핸드폰 */
	private String memDelYn; /* 탈퇴여부 */

	// ----------

	@Override
	public String toString() {
		// apache commons Lang ToStringBuilder
		return ToStringBuilder.reflectionToString(this);
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemAuth() {
		return memAuth;
	}

	public void setMemAuth(String memAuth) {
		this.memAuth = memAuth;
	}

	public String getMemSex() {
		return memSex;
	}

	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemBir() {
		return memBir;
	}

	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}

	public String getMemZip() {
		return memZip;
	}

	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}

	public String getMemAdd1() {
		return memAdd1;
	}

	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}

	public String getMemAdd2() {
		return memAdd2;
	}

	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}

	public String getMemHp() {
		return memHp;
	}

	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}

	public String getMemDelYn() {
		return memDelYn;
	}

	public void setMemDelYn(String memDelYn) {
		this.memDelYn = memDelYn;
	}

}
