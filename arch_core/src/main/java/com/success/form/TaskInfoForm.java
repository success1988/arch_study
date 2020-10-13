package com.success.form;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskInfoForm extends BaseForm {

	/**
	 * 任务编号
	 */
	private String code;

	private String name;

	private String clusterip;

	private Integer status;
}
