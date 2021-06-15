package com.movie.main.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class CompanyVO implements Serializable {
	
	private String companyCd;     
	private String companyNm;     
	private String companyPartNm;
	private String movieCd;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getCompanyNm() {
		return companyNm;
	}

	public String getCompanyPartNm() {
		return companyPartNm;
	}

	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}

	public void setCompanyPartNm(String companyPartNm) {
		this.companyPartNm = companyPartNm;
	}

	public String getMovieCd() {
		return movieCd;
	}

	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	
}
