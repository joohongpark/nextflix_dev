package com.movie.common.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResultMessageVO {
	private boolean result;
	private String title;
	private String message;
	private String url;
	private String urlTitle;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public boolean isResult() {
		return result;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public String getUrl() {
		return url;
	}

	public String getUrlTitle() {
		return urlTitle;
	}

	public ResultMessageVO setResult(boolean result) {
		this.result = result;
		return this;
	}

	public ResultMessageVO setTitle(String title) {
		this.title = title;
		return this;
	}

	public ResultMessageVO setMessage(String message) {
		this.message = message;
		return this;
	}

	public ResultMessageVO setUrl(String url) {
		this.url = url;
		return this;
	}

	public ResultMessageVO setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
		return this;
	}

}
