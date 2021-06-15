package com.movie.exception;

@SuppressWarnings("serial")
public class BizNotFoundException  extends BizException{

	public BizNotFoundException() {
		super();
	}

	public BizNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BizNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizNotFoundException(String message) {
		super(message);
	}

	public BizNotFoundException(Throwable cause) {
		super(cause);
	}

}
