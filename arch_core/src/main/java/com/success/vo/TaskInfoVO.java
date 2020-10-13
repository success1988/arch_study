package com.success.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskInfoVO {

	private Integer id;

	/**
	 * 任务编号
	 */
	private String code;

	private String name;

	private String description;

	private String expression;

	private String jobclass;

	private String clusterip;

	private String params;

	private Integer status;

	private String statusName;

	private String createTime;

	private String startTime;

	private String stopTime;
}
