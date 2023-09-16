package com.orjujeng.profile.exception;

public class BindingInfoNotMatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errMsg;
	public BindingInfoNotMatchException(String errMsg) {
        super(errMsg);
	}
}

