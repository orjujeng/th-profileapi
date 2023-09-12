package com.orjujeng.profile.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.orjujeng.profile.entity.MemberInfo;
import com.orjujeng.profile.entity.MemberInfoLog;

@Mapper
public interface MemberMapper {
	public List<MemberInfo> getMemberinfoByAccount(@Param("account_member") String account);

	public List<MemberInfo> getAllMemberinfo();

	public boolean updateMemberinfoByAccountNum(MemberInfo updateInfo);

	public boolean insertMemberinfoLog(MemberInfoLog memberInfoLog);

	public boolean insertMemberInfo(MemberInfo memberInfo);

	public MemberInfo getMemberInfoByMaxId();
}
