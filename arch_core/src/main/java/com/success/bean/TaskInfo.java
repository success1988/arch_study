package com.success.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Setter
@Getter
public class TaskInfo {

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

    private Date createTime;

    private Date startTime;

    private Date stopTime;
}