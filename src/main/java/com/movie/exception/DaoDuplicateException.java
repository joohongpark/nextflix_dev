package com.movie.exception;

@SuppressWarnings("serial")
public class DaoDuplicateException  extends DaoException{

	public DaoDuplicateException() {
		super();
	}

	public DaoDuplicateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DaoDuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoDuplicateException(String message) {
		super(message);
	}

	public DaoDuplicateException(Throwable cause) {
		super(cause);
	}

}
