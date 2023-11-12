package com.orjujeng.profile.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import com.orjujeng.profile.entity.MemberInfo;
import com.orjujeng.profile.entity.MemberInfoLog;

@Mapper
public interface MemberMapper {
	public List<MemberInfo> getMemberinfoByAccount(@Param("account_member") String account);
	@Cacheable(value = "PROFILE",key = "#root.methodName")
	public List<MemberInfo> getAllMemberinfo();

	public boolean updateMemberinfoByAccountNum(MemberInfo updateInfo);

	public boolean insertMemberinfoLog(MemberInfoLog memberInfoLog);

	public int insertMemberInfo(MemberInfo memberInfo);

	public MemberInfo getMemberInfoByMaxId();
	
	public List<MemberInfo> getMemberInfoById(Integer id);
	
	public void deleteMemberInfo(Integer id);
	
	public List<MemberInfo> getMemberByManagerId(Integer memberId);
}
