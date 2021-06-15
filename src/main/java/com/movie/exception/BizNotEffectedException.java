package com.movie.exception;

@SuppressWarnings("serial")
public class BizNotEffectedException  extends BizException{

	private Object source ;
	
	public Object getSource() {
		return source;
	}
	
	public void setSource(Object source) {
		this.source = source;
	}
	
	public BizNotEffectedException() {
		super();
	}

	public BizNotEffectedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BizNotEffectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizNotEffectedException(String message) {
		super(message);
	}

	public BizNotEffectedException(String message, Object source) {
		super(message + "\r\n" + source.toString() );
		this.source = source;
	}
	
	public BizNotEffectedException(Throwable cause) {
		super(cause);
	}

}
