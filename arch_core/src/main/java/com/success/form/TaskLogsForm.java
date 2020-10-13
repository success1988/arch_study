package com.success.form;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
public class TaskLogsForm extends BaseForm {

	/**
	 * 任务编号
	 */
	private String code;

	/**
	 * 类型：S-启动 R-运行 P-停止
	 */
	private String type;

	/**
	 * 执行结果
	 */
	private Integer status;

	@Override
	public String getDescField() {
		String desc = super.getDescField();
		if(StringUtils.isBlank(desc)){
			desc = "id";
		}
		return desc;
	}
}
