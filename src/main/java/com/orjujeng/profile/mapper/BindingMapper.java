package com.orjujeng.profile.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.orjujeng.profile.entity.BindingInfo;
import com.orjujeng.profile.entity.BindingInfoLog;

public interface BindingMapper {

	List<BindingInfo> getBindingInfo(@Param("member_id") Integer memberId, @Param("accountNum") String accountNum);

	void updateBindInfo(BindingInfo updateBindingInfo);

	void insertBindInfoLog(BindingInfoLog log);

}
