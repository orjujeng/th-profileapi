package com.orjujeng.profile.exception;

public class AccountNotExistException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String errMsg;
	public AccountNotExistException(String errMsg) {
        super(errMsg);
	}
}
