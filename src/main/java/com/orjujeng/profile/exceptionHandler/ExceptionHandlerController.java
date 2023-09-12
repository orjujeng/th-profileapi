package com.orjujeng.profile.exceptionHandler;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orjujeng.profile.exception.AccountMultipleException;
import com.orjujeng.profile.exception.AccountNotExistException;
import com.orjujeng.profile.exception.CreateNewProjectMsgUnfitExcption;
import com.orjujeng.profile.exception.CreateProfileMsgUnfitException;
import com.orjujeng.profile.exception.ProjectInfoIdNotFoundException;
import com.orjujeng.profile.utils.Result;
import com.orjujeng.profile.utils.ResultCode;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {
	@ResponseBody
	@ExceptionHandler(value = AccountNotExistException.class)
	public Result accountNotExistExceptionHandler(AccountNotExistException e){
		log.error(e.getMessage(),e);
       	return Result.error(ResultCode.ACCOUNT_NUM_NOT_FOUND.code,e.getMessage(),null);
    }
	@ResponseBody
	@ExceptionHandler(value = BindException.class)
    public Result validExceptionHandler(BindException e){
		log.error(e.getMessage(),e);
		return Result.error(ResultCode.FAIL.code,e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),null);
	}
	
	@ResponseBody
	@ExceptionHandler(value = AccountMultipleException.class)
    public Result accountNotExistExceptionHandler(AccountMultipleException e){
		log.error(e.getMessage(),e);
		return Result.error(ResultCode.FAIL.code,e.getMessage(),null);
	}
	
	@ResponseBody
	@ExceptionHandler(value = CreateProfileMsgUnfitException.class)
    public Result createProfileMsgUnfitExceptionHandler(CreateProfileMsgUnfitException e){
		log.error(e.getMessage(),e);
		return Result.error(ResultCode.FAIL.code,e.getMessage(),null);
	}
	
	@ResponseBody
	@ExceptionHandler(value = ProjectInfoIdNotFoundException.class)
    public Result projectInfoIdNotFoundExceptionHandler(ProjectInfoIdNotFoundException e){
		log.error(e.getMessage(),e);
		return Result.error(ResultCode.FAIL.code,e.getMessage(),null);
	}
	
	@ResponseBody
	@ExceptionHandler(value = CreateNewProjectMsgUnfitExcption.class)
    public Result createNewProjectMsgUnfitExcptionHandler(CreateNewProjectMsgUnfitExcption e){
		log.error(e.getMessage(),e);
		return Result.error(ResultCode.FAIL.code,e.getMessage(),null);
	}
	
}
