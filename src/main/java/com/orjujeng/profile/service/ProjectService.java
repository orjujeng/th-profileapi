package com.orjujeng.profile.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Service;

import com.orjujeng.profile.entity.ProjectInfo;

@Service
public interface ProjectService {

	public List<ProjectInfo> getAllProject(Boolean expiredDate);

	public ProjectInfo getProjectById(int id);

	public void updateProjectById(ProjectInfo projectInfo);

	public void addProject(ProjectInfo projectInfo);

	public void disableProject(Integer id);

}
