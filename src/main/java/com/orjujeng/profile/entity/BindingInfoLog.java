package com.orjujeng.profile.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BindingInfoLog implements Serializable{
	private Integer seqId;
	private Integer id;
	private Integer memberId;
	private String  accountNum;
	private String projectCode;
	private Date startDate;
	private Date expireDate;
	private Integer proportion;
	private Date lastUpdateDate;
	private String changedBy;
	private String action;
}
