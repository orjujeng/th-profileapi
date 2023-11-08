package com.orjujeng.profile.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orjujeng.profile.entity.ProjectInfo;
import com.orjujeng.profile.exception.CreateNewProjectMsgUnfitExcption;
import com.orjujeng.profile.exception.ProjectInfoIdNotFoundException;
import com.orjujeng.profile.mapper.ProjectMapper;
import com.orjujeng.profile.service.ProjectService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectMapper projectMapper;
	@Override
	public List<ProjectInfo> getAllProject(Boolean expiredDate) {
		List<ProjectInfo> result = null; 
		if(expiredDate == null || expiredDate == false ) {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(now);
			result = projectMapper.getAllProjectinfo(date);
		}else {
			result = projectMapper.getAllProjectinfo(null);
		}
		return result;
	}
	
	@Override
	public ProjectInfo getProjectById(int id) {
		
		ProjectInfo result = projectMapper.getProjectById(id);
		
		if(result == null) {
			throw new ProjectInfoIdNotFoundException("Project Id Not Find");
		}
		return result;
		
	}

	@Override
	@Transactional
	public void updateProjectById(ProjectInfo projectInfo) {
		ProjectInfo result = this.getProjectById(projectInfo.getId());
		ProjectInfo updateInfo = new ProjectInfo();
		updateInfo.setProjectCode(result.getProjectCode().equals(projectInfo.getProjectCode())?null:projectInfo.getProjectCode());
		updateInfo.setProjectName(result.getProjectName().equals(projectInfo.getProjectName())?null:projectInfo.getProjectName());
		updateInfo.setExpireDate(result.getExpireDate().toString().equals(projectInfo.getExpireDate().toString()) ?null:projectInfo.getExpireDate());
		if(updateInfo.getExpireDate() == null &&updateInfo.getProjectName() == null && updateInfo.getProjectCode() == null) {
			return;
		}else {
		updateInfo.setId(result.getId());
		log.info(updateInfo.toString());
		projectMapper.updateProjectInfo(updateInfo);
		}
	}

	@Override
	@Transactional
	public void addProject(ProjectInfo projectInfo) {
		if(projectInfo.getProjectCode() == null || projectInfo.getExpireDate() == null || projectInfo.getProjectName() == null ) {
			throw new CreateNewProjectMsgUnfitExcption("Create New Project Msg Unfit");
		}else {
			projectMapper.insertNewProject(projectInfo);
		}
		
	}

	@Override
	public void disableProject(Integer id) {
		ProjectInfo result = this.getProjectById(id);
		Date now = new Date();
		java.sql.Date sqlDate = new java.sql.Date(now.getTime());
		result.setExpireDate(sqlDate);
		projectMapper.updateProjectInfo(result);
	}

}
