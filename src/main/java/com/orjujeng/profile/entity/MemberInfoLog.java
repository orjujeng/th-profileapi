package com.orjujeng.profile.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberInfoLog implements Serializable{
	private int seqId;
	private Integer id;
	private String nameZh;
	private String nameCn;
	private String accountNum;
	private String managerId;
	private String perm;
	private Date joinDate;
	private Date expiredDate;
	private Date lastUpdatedDate;
	private String authOfBackend;
	private String updateBy;
	private String action;
	private String deleteFlag;
}
