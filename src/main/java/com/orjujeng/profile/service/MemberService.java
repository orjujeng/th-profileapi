package com.orjujeng.profile.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.orjujeng.profile.entity.MemberInfo;
import com.orjujeng.profile.utils.Result;
@Service
public interface MemberService {
	List<MemberInfo> getMemberinfo(String account);

	Result updateMemberinfo(MemberInfo memberInfo);

	Result createMemberinfo(MemberInfo memberInfo);

	List<MemberInfo> getMemberInfoById(Integer id);
}
