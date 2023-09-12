package com.orjujeng.profile.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orjujeng.profile.entity.MemberInfo;
import com.orjujeng.profile.entity.MemberInfoLog;
import com.orjujeng.profile.exception.AccountMultipleException;
import com.orjujeng.profile.exception.AccountNotExistException;
import com.orjujeng.profile.exception.CreateProfileMsgUnfitException;
import com.orjujeng.profile.mapper.MemberMapper;
import com.orjujeng.profile.service.MemberService;
import com.orjujeng.profile.utils.Result;
import com.orjujeng.profile.utils.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public List<MemberInfo> getMemberinfo(String accountNum) {
		if (accountNum == null || accountNum == "") {
			List<MemberInfo> result = memberMapper.getAllMemberinfo();
			log.info(result.toString());
			return result;
		}else {
			List<MemberInfo> result = memberMapper.getMemberinfoByAccount(accountNum);
			log.info(result.toString());
			return result;
		}
	}

	@Override
	@Transactional
	public Result updateMemberinfo(MemberInfo memberInfo) {
		List<MemberInfo> memberinfos  = this.getMemberinfo(memberInfo.getAccountNum());
		if(memberinfos == null || memberinfos.isEmpty()) {
			throw new AccountNotExistException("AccountNum "+ memberInfo.getAccountNum() +" Not Exist");
		}else if (memberinfos.size() > 1){
			throw new AccountMultipleException("AccountNum Have Multiple Record");
		}else {
			MemberInfo updateInfo = new MemberInfo();
			updateInfo.setPerm(memberInfo.getPerm() == null ? null : memberInfo.getPerm().equals(memberinfos.get(0).getPerm()) ? null : memberInfo.getPerm());
			updateInfo.setNameCn(memberInfo.getNameCn() ==null ? null : memberInfo.getNameCn().equals(memberinfos.get(0).getNameCn()) ? null : memberInfo.getNameCn());
			updateInfo.setNameZh(memberInfo.getNameZh()==null ? null : memberInfo.getNameZh().equals(memberinfos.get(0).getNameZh())? null : memberInfo.getNameZh());
			updateInfo.setExpiredDate(memberInfo.getExpiredDate() == null ? null : memberInfo.getExpiredDate().equals(memberinfos.get(0).getExpiredDate()) ? null : memberInfo.getExpiredDate());
			updateInfo.setJoinDate(memberInfo.getJoinDate() == null ? null : memberInfo.getJoinDate().equals(memberinfos.get(0).getJoinDate()) ? null: memberInfo.getJoinDate());
			updateInfo.setManagerId(memberInfo.getManagerId()==null ? null: memberInfo.getManagerId().equals(memberinfos.get(0).getManagerId()) ? null : memberInfo.getManagerId());
			updateInfo.setAuthOfBackend(memberInfo.getAuthOfBackend()==null ? null : memberInfo.getAuthOfBackend().equals(memberinfos.get(0).getAuthOfBackend()) ? null : memberInfo.getAuthOfBackend());
			updateInfo.setAccountNum(memberInfo.getAccountNum());
			updateInfo.setId(memberinfos.get(0).getId());
			updateInfo.setDeleteFlag(memberinfos.get(0).getDeleteFlag());
			if (updateInfo.getPerm() == null && updateInfo.getAuthOfBackend() == null && updateInfo.getNameZh() == null && updateInfo.getNameCn() == null && updateInfo.getManagerId() == null && updateInfo.getJoinDate() == null && updateInfo.getExpiredDate() == null) {
				return Result.success(null);
			} else {
				boolean result = memberMapper.updateMemberinfoByAccountNum(updateInfo);
				if (result == true) {
					MemberInfoLog memberInfoLog = new MemberInfoLog();
					//To Do add the request orgin
					memberInfoLog.setUpdateBy("Profile Api");
					memberInfoLog.setAction("Update");
					BeanUtils.copyProperties(updateInfo,memberInfoLog);
					//
					try {
						memberMapper.insertMemberinfoLog(memberInfoLog);
					} catch (Exception e) {
						e.printStackTrace();
						return Result.successWithMsg("insert MemberinfoLog Failed, please check",null);
					}
					return Result.success(null);
				}else {
					return Result.error(ResultCode.DATA_UPDATE_ERROR.code, "MemberInfo Data Update Error", null);
				}
			}
		}
	}

	@Override
	@Transactional
	public Result createMemberinfo(MemberInfo memberInfo) {
		if(memberInfo.getNameCn() == null || memberInfo.getNameZh() == null || memberInfo.getPerm() == null || memberInfo.getAuthOfBackend() == null) {
			throw new CreateProfileMsgUnfitException("Create Profile Msg Unfit");
		}else {
			boolean result = memberMapper.insertMemberInfo(memberInfo);
			if (result == true) {
				MemberInfo insertInfo = memberMapper.getMemberInfoByMaxId();
				if(insertInfo.getNameCn().equals(memberInfo.getNameCn()) && insertInfo.getNameZh().equals(memberInfo.getNameZh()) && insertInfo.getPerm().equals(memberInfo.getPerm()) && insertInfo.getAuthOfBackend().equals(memberInfo.getAuthOfBackend())) {
					MemberInfoLog memberInfoLog = new MemberInfoLog();
					//To Do add the request orgin
					memberInfoLog.setUpdateBy("Profile Api");
					memberInfoLog.setAction("Insert");
					BeanUtils.copyProperties(insertInfo,memberInfoLog);
					//
					try {
						memberMapper.insertMemberinfoLog(memberInfoLog);
					} catch (Exception e) {
						e.printStackTrace();
						return Result.successWithMsg("insert MemberinfoLog Failed, Please check",null);
					}
					return Result.success(null);
				}else {
					return Result.error(ResultCode.DATA_INSTER_ERROR.code, "MemberInfo Data Insert Error", null);
				}
			}
		}
		 return Result.error(ResultCode.DATA_INSTER_ERROR.code, "MemberInfo Data Insert Error", null);
	}
}
