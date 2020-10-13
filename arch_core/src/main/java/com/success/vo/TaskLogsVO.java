package com.success.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskLogsVO {

	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 任务编号
	 */
	private String code;

	/**
	 * 任务名称
	 */
	private String name;

	/**
	 * 执行表达式
	 */
	private String expression;

	/**
	 * 类型：S-启动 R-运行 P-停止
	 */
	private String type;

	/**
	 * 类型描述
	 */
	private String typeText;

	/**
	 * 开始时间戳
	 */
	private String startTime;

	/**
	 * 结束时间
	 */
	private String endTime;

	/**
	 * 执行时间，ms
	 */
	private Long execTime;

	/**
	 * 主机IP
	 */
	private String host;

	/**
	 * 执行结果
	 */
	private Integer status;

	/**
	 * 状态描述
	 */
	private String statusText;

	/**
	 * 错误信息
	 */
	private String errorMsg;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private String createTime;
}
