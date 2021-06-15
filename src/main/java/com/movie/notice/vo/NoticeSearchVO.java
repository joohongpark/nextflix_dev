package com.movie.notice.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.movie.common.vo.PagingVO;

@SuppressWarnings("serial")
public class NoticeSearchVO extends PagingVO {
	private String searchType;
	private String searchWord;
	private String searchCategory;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this , ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	
	
	
	
}
