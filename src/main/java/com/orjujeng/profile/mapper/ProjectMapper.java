package com.orjujeng.profile.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import com.orjujeng.profile.entity.ProjectInfo;
@Mapper
public interface ProjectMapper {
	
	public List<ProjectInfo> getAllProjectinfo(@Param("date") String date);

	public ProjectInfo getProjectById(@Param("id") int id);

	public void updateProjectInfo(ProjectInfo projectInfo);

	public void insertNewProject(ProjectInfo projectInfo);
}
