package com.orjujeng.profile.exception;

public class CreateProfileMsgUnfitException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String errMsg;
	public CreateProfileMsgUnfitException(String errMsg) {
        super(errMsg);
	}
}
