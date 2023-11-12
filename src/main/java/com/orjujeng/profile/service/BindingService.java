package com.orjujeng.profile.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.orjujeng.profile.entity.AddBindingInfo;
import com.orjujeng.profile.entity.BindingInfo;
import com.orjujeng.profile.entity.UpdateBindingInfo;
@Service
public interface BindingService {

	void updateBindingInfo(UpdateBindingInfo bindingInfo);
	public List<BindingInfo> checkBindingInfo(String id, String memberId, String accountNum, String projectCode);

}
