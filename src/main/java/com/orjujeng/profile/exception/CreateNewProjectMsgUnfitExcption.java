package com.orjujeng.profile.exception;

public class CreateNewProjectMsgUnfitExcption extends RuntimeException{

	private static final long serialVersionUID = -6225794739902361564L;
	private String errMsg;
	public CreateNewProjectMsgUnfitExcption(String errMsg) {
        super(errMsg);
	}
}
