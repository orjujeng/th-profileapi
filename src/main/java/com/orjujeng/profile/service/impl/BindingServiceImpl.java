package com.orjujeng.profile.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orjujeng.profile.entity.BindingInfo;
import com.orjujeng.profile.entity.BindingInfoLog;
import com.orjujeng.profile.entity.UpdateBindingInfo;
import com.orjujeng.profile.exception.AccountNotExistException;
import com.orjujeng.profile.mapper.BindingMapper;
import com.orjujeng.profile.service.BindingService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class BindingServiceImpl implements BindingService{
	@Autowired
	BindingMapper bindingMapper;
	
	@Override
	@Transactional
	public void updateBindingInfo(UpdateBindingInfo bindingInfo) {
		Integer memberId = bindingInfo.getMemberId();
		String accountNum = bindingInfo.getAccountNum();
		List<BindingInfo> result = bindingMapper.getBindingInfo(memberId,accountNum);
		if (result == null || result.size() != 1 ) {
			throw new AccountNotExistException("Binding Info Not Exist");
		}
		BindingInfo updateBindingInfo = new BindingInfo();
		String oldProjectCode = result.get(0).getProjectCode();
		Integer oldProportion = result.get(0).getProportion();
		updateBindingInfo.setId(result.get(0).getId());
		updateBindingInfo.setProjectCode(bindingInfo.getProjectCode().equals(oldProjectCode)?null:bindingInfo.getProjectCode());
		updateBindingInfo.setProportion(bindingInfo.getProportion() == oldProportion?null:bindingInfo.getProportion());
		
		updateBindingInfo.setStartDate(bindingInfo.getStartDate().equals(result.get(0).getStartDate())?null:bindingInfo.getStartDate());
		updateBindingInfo.setExpireDate(bindingInfo.getExpireDate().equals(result.get(0).getExpireDate())?null:bindingInfo.getExpireDate());
		if(updateBindingInfo.getProjectCode() == null && updateBindingInfo.getProportion() == null && updateBindingInfo.getStartDate() == null && updateBindingInfo.getExpireDate() == null) {
			return;
		}
		updateBindingInfo.setChangedBy(bindingInfo.getChangedBy());
		updateBindingInfo.setMemberId(memberId);
		updateBindingInfo.setAccountNum(accountNum);
		bindingMapper.updateBindInfo(updateBindingInfo);
		BindingInfoLog log_info  = new BindingInfoLog();
		BeanUtils.copyProperties(updateBindingInfo, log_info);
		log_info.setAction("Update");
		bindingMapper.insertBindInfoLog(log_info);
	}

}
