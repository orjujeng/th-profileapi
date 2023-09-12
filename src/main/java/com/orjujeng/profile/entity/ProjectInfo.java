package com.orjujeng.profile.entity;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import reactor.util.annotation.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectInfo {
	@NonNull
	private Integer id;
	private String projectCode;
	private String projectName;
	private Date expireDate;
	private Date lastUpdatedDate;
}
