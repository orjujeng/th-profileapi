package com.orjujeng.profile.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orjujeng.profile.entity.AddBindingInfo;
import com.orjujeng.profile.entity.BindingInfo;
import com.orjujeng.profile.entity.ProjectInfo;
import com.orjujeng.profile.entity.UpdateBindingInfo;
import com.orjujeng.profile.service.BindingService;
import com.orjujeng.profile.utils.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/profile/binding")
public class BindingController {
	@Autowired
	BindingService bindingService;
	@PostMapping("/updatebinding")
	public Result updatebinding(@RequestBody @Valid UpdateBindingInfo bindingInfo) {
		bindingService.updateBindingInfo(bindingInfo);
		return Result.successWithMsg(null,"update Binding Info Success");
	}
	
	@PostMapping("/addbinding")
	public Result addbinding(@RequestBody @Valid AddBindingInfo bindingInfo) {
		bindingService.addBindingInfo(bindingInfo);
		return Result.successWithMsg(null,"Insert Binding Info Success");
	}
	
	@GetMapping("/findbinding")
	public Result findbinding(@RequestParam(required = false) String id,@RequestParam(required = false)String memberId,@RequestParam(required = false) @Length(min = 8, max = 8, message = "Account Number Must 8 Digs") String accountNum,@RequestParam(required = false) String projectCode) {
		List<BindingInfo> result = bindingService.checkBindingInfo(id,memberId,accountNum,projectCode);
		return Result.successWithMsg(result,"Success");
	}
}
