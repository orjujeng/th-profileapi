package com.orjujeng.profile.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public Result getAllbinding(@RequestBody @Valid UpdateBindingInfo bindingInfo) {
		bindingService.updateBindingInfo(bindingInfo);
		return Result.successWithMsg("update Binding Info Success",null);
	}
}
