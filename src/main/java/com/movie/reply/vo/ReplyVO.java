package com.movie.reply.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class ReplyVO  implements Serializable{
	private String reCategory;     /* 분류(BOARD, PDS, ...) */
	private int reParentNo;     /* 부모 번호 */
	private String reMemId;        /* 작성자ID */
	private String reMemName;     	/* 작성자 이름 	*/	
	private String reContent;      /* 댓글 내용 */
	private String reIp;           /* IP */
	private String reRegDate;      /* 댓글 등록일자 */
	private String reModDate;      /* 댓글 수정일자 */
	private int reNo;           /* 댓글번호 */
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getReCategory() {
		return reCategory;
	}

	public int getReParentNo() {
		return reParentNo;
	}

	public String getReMemId() {
		return reMemId;
	}

	public String getReMemName() {
		return reMemName;
	}

	public String getReContent() {
		return reContent;
	}

	public String getReIp() {
		return reIp;
	}

	public String getReRegDate() {
		return reRegDate;
	}

	public String getReModDate() {
		return reModDate;
	}

	public int getReNo() {
		return reNo;
	}

	public void setReCategory(String reCategory) {
		this.reCategory = reCategory;
	}

	public void setReParentNo(int reParentNo) {
		this.reParentNo = reParentNo;
	}

	public void setReMemId(String reMemId) {
		this.reMemId = reMemId;
	}

	public void setReMemName(String reMemName) {
		this.reMemName = reMemName;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public void setReIp(String reIp) {
		this.reIp = reIp;
	}

	public void setReRegDate(String reRegDate) {
		this.reRegDate = reRegDate;
	}

	public void setReModDate(String reModDate) {
		this.reModDate = reModDate;
	}

	public void setReNo(int reNo) {
		this.reNo = reNo;
	}
	
	
	
}
