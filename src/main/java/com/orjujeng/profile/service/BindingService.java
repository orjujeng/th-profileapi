package com.orjujeng.profile.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.orjujeng.profile.entity.UpdateBindingInfo;
@Service
public interface BindingService {

	void updateBindingInfo(UpdateBindingInfo bindingInfo);

}
