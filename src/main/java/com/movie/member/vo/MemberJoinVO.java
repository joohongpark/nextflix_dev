package com.movie.member.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
public class MemberJoinVO extends MemberVO {
	
	private String agreeYn ;  
	private String privacyYn;
	private String eventYn;
	private String memPassConfirm;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getMemPassConfirm() {
		return memPassConfirm;
	}

	public void setMemPassConfirm(String memPassConfirm) {
		this.memPassConfirm = memPassConfirm;
	}


	public String getAgreeYn() {
		return agreeYn;
	}

	public void setAgreeYn(String agreeYn) {
		this.agreeYn = agreeYn;
	}

	public String getPrivacyYn() {
		return privacyYn;
	}

	public void setPrivacyYn(String privacyYn) {
		this.privacyYn = privacyYn;
	}

	public String getEventYn() {
		return eventYn;
	}

	public void setEventYn(String eventYn) {
		this.eventYn = eventYn;
	}
	
	
}
