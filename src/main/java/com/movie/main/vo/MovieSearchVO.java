package com.movie.main.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.movie.common.vo.PagingVO;

@SuppressWarnings("serial")
public class MovieSearchVO extends PagingVO {
	
	private String searchCategory;	
	private String searchWord;	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public String getSearchCategory() {
		return searchCategory;
	}
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	
}
