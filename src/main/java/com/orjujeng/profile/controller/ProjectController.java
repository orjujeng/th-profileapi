package com.orjujeng.profile.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orjujeng.profile.entity.MemberInfo;
import com.orjujeng.profile.entity.ProjectInfo;
import com.orjujeng.profile.service.ProjectService;
import com.orjujeng.profile.utils.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/profile/project")
public class ProjectController {
	@Autowired
	ProjectService projectService;
	
	@GetMapping("/getAllProject")
	public Result getAllProject(@RequestParam(required = false) Boolean expiredDate) {
		List<ProjectInfo> result = projectService.getAllProject(expiredDate);
		return Result.success(result);
	}
	
	@PostMapping("/updateProject")
	public Result updateProject(@Valid @RequestBody ProjectInfo projectInfo) {
		projectService.updateProjectById(projectInfo);
		return Result.success("Update Successful");
	}
	
	@PostMapping("/addProject")
	public Result addProject(@RequestBody ProjectInfo projectInfo) {
		projectService.addProject(projectInfo);
		return Result.success("Insert Successful");
	}
	
	@PostMapping("/disableProject")
	public Result disableProject(@RequestParam(required = false) @Pattern (regexp="/^[0-9]*$/", message = "Must Be Correct Id")Integer id) {
		projectService.disableProject(id);
		return Result.success("Disable Successful");
	}
	
}
