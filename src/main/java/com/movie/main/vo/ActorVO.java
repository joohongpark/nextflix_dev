package com.movie.main.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class ActorVO implements Serializable {

	/* Actors Table */
	private String actorCd;
	private String actorNm;
	private String actorNmEn;

	/* Movie Actors Table */
	private String cast;
	private String movieCd;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getActorCd() {
		return actorCd;
	}

	public String getActorNm() {
		return actorNm;
	}

	public String getActorNmEn() {
		return actorNmEn;
	}

	public String getCast() {
		return cast;
	}

	public String getMovieCd() {
		return movieCd;
	}

	public void setActorCd(String actorCd) {
		this.actorCd = actorCd;
	}

	public void setActorNm(String actorNm) {
		this.actorNm = actorNm;
	}

	public void setActorNmEn(String actorNmEn) {
		this.actorNmEn = actorNmEn;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}

}
