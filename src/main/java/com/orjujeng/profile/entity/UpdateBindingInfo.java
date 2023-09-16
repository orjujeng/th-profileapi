package com.orjujeng.profile.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBindingInfo {
	private Integer id;
	@NotNull
	private Integer memberId;
	private String  accountNum;
	private String projectCode;
	private Date startDate;
	private Date expireDate;
	private Integer proportion;
	private Date lastUpdateDate;
	private String changedBy;
}
