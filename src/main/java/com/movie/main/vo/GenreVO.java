package com.movie.main.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class GenreVO implements Serializable{
	
	private String genreCd; 
	private String genreNm; 
	private String movieCd;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getGenreCd() {
		return genreCd;
	}

	public String getGenreNm() {
		return genreNm;
	}

	public void setGenreCd(String genreCd) {
		this.genreCd = genreCd;
	}

	public void setGenreNm(String genreNm) {
		this.genreNm = genreNm;
	}

	public String getMovieCd() {
		return movieCd;
	}

	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}
	
	
}
