package com.movie.main.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class MovieVO implements Serializable{
	private String movieCd;       
	private String movieNm;       
	private String showTm;        
	private String openDt;        
	private String movieImg;      
	private String movieContent;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public String getMovieCd() {
		return movieCd;
	}
	public String getMovieNm() {
		return movieNm;
	}
	public String getShowTm() {
		return showTm;
	}
	public String getOpenDt() {
		return openDt;
	}
	public String getMovieImg() {
		return movieImg;
	}
	public String getMovieContent() {
		return movieContent;
	}
	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}
	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}
	public void setShowTm(String showTm) {
		this.showTm = showTm;
	}
	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}
	public void setMovieImg(String movieImg) {
		this.movieImg = movieImg;
	}
	public void setMovieContent(String movieContent) {
		this.movieContent = movieContent;
	}
	
}
