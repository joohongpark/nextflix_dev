package com.movie.exception;

@SuppressWarnings("serial")
public class BizDuplicateException  extends BizException{

	public BizDuplicateException() {
		super();
	}

	public BizDuplicateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BizDuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizDuplicateException(String message) {
		super(message);
	}

	public BizDuplicateException(Throwable cause) {
		super(cause);
	}

}
