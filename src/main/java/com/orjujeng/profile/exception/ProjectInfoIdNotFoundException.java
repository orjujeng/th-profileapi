package com.orjujeng.profile.exception;

public class ProjectInfoIdNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String errMsg;
	public ProjectInfoIdNotFoundException(String errMsg) {
        super(errMsg);
	}
}
