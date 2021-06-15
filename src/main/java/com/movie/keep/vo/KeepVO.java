package com.movie.keep.vo;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class KeepVO  implements Serializable{
	
	private String keMovieCd;		/* 영화 코드 */
	private String keMovieName;		/* 영화 제목 */
	private String keMemId;			/* 회원 아이디 */
	private String keOpenDt;		/* 개봉 일자 */
	private String keGenre;			/* 영화 장르 */
	private String keRegDt;			/* 찜한 날짜 */
	private int keMemAge;			/* 회원 연령 */
	private String keMemGender;		/* 회원 성별 */
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getKeMovieCd() {
		return keMovieCd;
	}

	public void setKeMovieCd(String keMovieCd) {
		this.keMovieCd = keMovieCd;
	}

	public String getKeMovieName() {
		return keMovieName;
	}

	public void setKeMovieName(String keMovieName) {
		this.keMovieName = keMovieName;
	}

	public String getKeMemId() {
		return keMemId;
	}

	public void setKeMemId(String keMemId) {
		this.keMemId = keMemId;
	}

	public String getKeOpenDt() {
		return keOpenDt;
	}

	public void setKeOpenDt(String keOpenDt) {
		this.keOpenDt = keOpenDt;
	}

	public String getKeGenre() {
		return keGenre;
	}

	public void setKeGenre(String keGenre) {
		this.keGenre = keGenre;
	}

	public String getKeRegDt() {
		return keRegDt;
	}

	public void setKeRegDt(String keRegDt) {
		this.keRegDt = keRegDt;
	}

	public int getKeMemAge() {
		return keMemAge;
	}

	public void setKeMemAge(int keMemAge) {
		this.keMemAge = keMemAge;
	}

	public String getKeMemGender() {
		return keMemGender;
	}

	public void setKeMemGender(String keMemGender) {
		this.keMemGender = keMemGender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(keMovieCd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof KeepVO)) {
			return false;
		}
		KeepVO other = (KeepVO) obj;
		return Objects.equals(keMovieCd, other.keMovieCd);
	}
	
	
}
