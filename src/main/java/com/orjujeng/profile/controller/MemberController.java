package com.orjujeng.profile.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orjujeng.profile.entity.MemberInfo;
import com.orjujeng.profile.exception.AccountNotExistException;
import com.orjujeng.profile.service.MemberService;
import com.orjujeng.profile.utils.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/profile/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/getMember")
	public Result getMemberInfoByAccountNum(@RequestParam(required = false) String accountNum) {
		List<MemberInfo> result  = memberService.getMemberinfo(accountNum);
		return Result.success(result);
	}
	@PostMapping("/updateMember")
	public Result updateMemberInfoByAccountNum(@Valid @RequestBody MemberInfo memberInfo) {
		Result result = memberService.updateMemberinfo(memberInfo);
		return Result.success(null);
	}
	
	@PostMapping("/addMember")
	public Result addNewProfile(@RequestBody MemberInfo memberInfo) {
		Result result = memberService.createMemberinfo(memberInfo);
		return result;
	}
	
	@GetMapping("/getMemberById")
	public Result getMemberInfoById(@RequestParam(required = false) Integer id) {
		List<MemberInfo> result  = memberService.getMemberInfoById(id);
		return Result.success(result);
	}
	
	@PostMapping("/deleteMember")
	public Result deleteMember(@RequestParam(required = true,value = "id") Integer id) {
		Result result = memberService.deleteMemberinfo(id);
		return result;
	}
	
	@GetMapping("/getMemberByManagerId")
	public Result getMemberByManagerId(@RequestParam(required = true,value = "memberId") Integer memberId) {
		
		List<MemberInfo> result  = memberService.getMemberByManagerId(memberId);
		return Result.success(result);
	}
}
