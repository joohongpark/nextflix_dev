package com.movie.main.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class DirectorVO implements Serializable {
	
	private String peopleCd;       /*  */
	private String peopleNm;       /*  */
	private String peopleNmEn;     /*  */
	private String movieCd;        /*  */
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getPeopleNm() {
		return peopleNm;
	}

	public String getPeopleNmEn() {
		return peopleNmEn;
	}

	public String getMovieCd() {
		return movieCd;
	}

	public void setPeopleNm(String peopleNm) {
		this.peopleNm = peopleNm;
	}

	public void setPeopleNmEn(String peopleNmEn) {
		this.peopleNmEn = peopleNmEn;
	}

	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}

	public String getPeopleCd() {
		return peopleCd;
	}

	public void setPeopleCd(String peopleCd) {
		this.peopleCd = peopleCd;
	}
	
}
