package com.orjujeng.profile.exception;

public class AccountMultipleException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String errMsg;
	public AccountMultipleException(String errMsg) {
        super(errMsg);
	}
}
