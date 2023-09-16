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

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class BindingServiceImpl implements BindingService{
	@Autowired
	BindingMapper bindingMapper;
	@Autowired
	MemberMapper memberMapper;
	@Override
	@Transactional
	public void updateBindingInfo(UpdateBindingInfo bindingInfo) {
		Integer memberId = bindingInfo.getMemberId();
		String accountNum = bindingInfo.getAccountNum();
		Integer id = bindingInfo.getId();
		List<BindingInfo> result = bindingMapper.getBindingInfo(memberId,accountNum,id);
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
		updateBindingInfo.setId(bindingInfo.getId());
		
		bindingMapper.updateBindInfo(updateBindingInfo);
		List<BindingInfo> res = bindingMapper.getBindingInfo(memberId, accountNum,null);
		int flag = 0;
		for (BindingInfo re : res) {
			// TODO change to update
			if(re.getProjectCode().equals(bindingInfo.getProjectCode())) {
				flag++;
			}	
		}
		if(flag > 1) {
			throw new BindingInfoNotMatchException("Updated Binding Project Code Already Exist In Other Row");
		}
		BindingInfoLog log_info  = new BindingInfoLog();
		BeanUtils.copyProperties(updateBindingInfo, log_info);
		log_info.setAction("Update");
		bindingMapper.insertBindInfoLog(log_info);
	}

	@Override
	@Transactional
	public void addBindingInfo(AddBindingInfo bindingInfo) {
		 
		Integer memberId = bindingInfo.getMemberId();
		String accountNum = bindingInfo.getAccountNum();
		List<MemberInfo> memberinfoByAccount = memberMapper.getMemberinfoByAccount(accountNum);
		if(memberinfoByAccount == null || memberinfoByAccount.size() != 1) {
			throw new AccountNotExistException("Not Found this AccountNum");
		}
		if(memberinfoByAccount.get(0).getId() != memberId){
			throw new BindingInfoNotMatchException("Binding Info Not Match");
		}
		List<BindingInfo> result = bindingMapper.getBindingInfo(memberId, accountNum,null);
		for (BindingInfo res : result) {
			// TODO change to update
			if(res.getProjectCode().equals(bindingInfo.getProjectCode()) ) {
				throw new BindingInfoNotMatchException("Binding Project Code already Exist");
			}
		}
		bindingMapper.insertBindingInfo(bindingInfo);
		BindingInfoLog log_info  = new BindingInfoLog();
		BeanUtils.copyProperties(bindingInfo, log_info);
		log_info.setAction("Insert");
		bindingMapper.insertBindInfoLog(log_info);
	}

	@Override
	public List<BindingInfo> checkBindingInfo(String id, String memberId, String accountNum) {
//		if(id == null && memberId ==null && accountNum ==null) {
//			throw new AccountNotExistException("Request Info with error");
//		}
		List<BindingInfo> result = bindingMapper.getBindingInfo(memberId == null ? null: Integer.valueOf(memberId), accountNum,id == null ? null:Integer.valueOf(id));
		return result;
	}

}
