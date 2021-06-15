package com.movie.keep.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.movie.common.vo.PagingVO;

@SuppressWarnings("serial")
public class KeepSearchVO extends PagingVO {
	
	private int keMovieCd;  	/* 영화 코드 */
	private String keMemId;
	private String keMovieGenre;
	private String searchType;
	private String searchWord;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getKeMovieCd() {
		return keMovieCd;
	}

	public void setKeMovieCd(int keMovieCd) {
		this.keMovieCd = keMovieCd;
	}

	public String getKeMemId() {
		return keMemId;
	}

	public void setKeMemId(String keMemId) {
		this.keMemId = keMemId;
	}

	public String getKeMovieGenre() {
		return keMovieGenre;
	}

	public void setKeMovieGenre(String keMovieGenre) {
		this.keMovieGenre = keMovieGenre;
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
	
	
}
