package com.movie.exception;

@SuppressWarnings("serial")
public class BizPasswordNotMatchedException  extends BizException{

	private Object source ;
	
	public Object getSource() {
		return source;
	}
	
	public void setSource(Object source) {
		this.source = source;
	}
	
	public BizPasswordNotMatchedException() {
		super();
	}

	public BizPasswordNotMatchedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BizPasswordNotMatchedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizPasswordNotMatchedException(String message) {
		super(message);
	}

	public BizPasswordNotMatchedException(String message, Object source) {
		super(message + "\r\n" + source.toString() );
		this.source = source;
	}
	
	public BizPasswordNotMatchedException(Throwable cause) {
		super(cause);
	}

}
