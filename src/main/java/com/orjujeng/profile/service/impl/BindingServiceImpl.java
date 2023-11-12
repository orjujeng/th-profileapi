package com.orjujeng.profile.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orjujeng.profile.entity.AddBindingInfo;
import com.orjujeng.profile.entity.BindingInfo;
import com.orjujeng.profile.entity.BindingInfoLog;
import com.orjujeng.profile.entity.MemberInfo;
import com.orjujeng.profile.entity.UpdateBindingInfo;
import com.orjujeng.profile.exception.AccountNotExistException;
import com.orjujeng.profile.exception.BindingInfoNotMatchException;
import com.orjujeng.profile.mapper.BindingMapper;
import com.orjujeng.profile.mapper.MemberMapper;
import com.orjujeng.profile.service.BindingService;
import com.orjujeng.profile.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BindingServiceImpl implements BindingService {
	@Autowired
	BindingMapper bindingMapper;
	@Autowired
	MemberMapper memberMapper;

	@Autowired
	MemberService memberService;

	@Override
	@Transactional
	public void updateBindingInfo(UpdateBindingInfo bindingInfo) {
		Integer memberId = bindingInfo.getMemberId();
		String accountNum = bindingInfo.getAccountNum();
		Integer id = bindingInfo.getId();
		List<BindingInfo> result = bindingMapper.getBindingInfo(memberId, accountNum, id, null);
		if (result == null) {
			throw new AccountNotExistException("Binding Info Not Exist");
		}
		memberService.deleteBindingInfo(result.get(0).getAccountNum());
		for(int i = 0;i<bindingInfo.getProjectCode().size();i++ ) {
			AddBindingInfo updateBindingInfo = new AddBindingInfo();
			updateBindingInfo.setAccountNum(result.get(0).getAccountNum());
			updateBindingInfo.setChangedBy(bindingInfo.getChangedBy());
			updateBindingInfo.setExpireDate(bindingInfo.getExpireDate().get(i));
			updateBindingInfo.setMemberId(bindingInfo.getMemberId());
			updateBindingInfo.setStartDate(bindingInfo.getStartDate().get(i));
			updateBindingInfo.setProjectCode(bindingInfo.getProjectCode().get(i));
			updateBindingInfo.setProportion(100/bindingInfo.getProjectCode().size());
			memberService.addBindingInfo(updateBindingInfo);
		}
	}

	
	@Override
	public List<BindingInfo> checkBindingInfo(String id, String memberId, String accountNum, String projectCode) {
		List<BindingInfo> result = bindingMapper.getBindingInfo(memberId == null ? null : Integer.valueOf(memberId),
				accountNum, id == null ? null : Integer.valueOf(id), projectCode);
		return result;
	}
}
