package com.success.bean;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TaskLogs {
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
     * 开始时间戳
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

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
    private Date createTime;
}
