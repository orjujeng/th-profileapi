package com.orjujeng.profile.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.orjujeng.profile.entity.AddBindingInfo;
import com.orjujeng.profile.entity.BindingInfo;
import com.orjujeng.profile.entity.BindingInfoLog;

public interface BindingMapper {

	List<BindingInfo> getBindingInfo(@Param("member_id") Integer memberId, @Param("account_num") String accountNum, @Param("id") Integer id);

	void updateBindInfo(BindingInfo updateBindingInfo);

	void insertBindInfoLog(BindingInfoLog log);

	void insertBindingInfo(AddBindingInfo bindingInfo);

}
