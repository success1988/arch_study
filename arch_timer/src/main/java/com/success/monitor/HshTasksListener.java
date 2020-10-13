package com.success.monitor;

import cn.fetosoft.rooster.monitor.TaskListener;

import com.success.bean.TaskInfo;
import com.success.bean.TaskLogs;
import com.success.enums.Status;
import com.success.enums.TasklogType;
import com.success.service.TaskInfoService;
import com.success.service.TaskLogsService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Title：
 * @Author：guo
 * @Date 2020/4/9 11:32
 * @Description
 * @Version
 */
@Component
public class HshTasksListener implements TaskListener {

	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private TaskLogsService taskLogsService;

	@Override
	public void start(cn.fetosoft.rooster.core.TaskInfo taskInfo, SchedulerException e) {
		TaskInfo task = new TaskInfo();
		task.setCode(taskInfo.getCode());
		task.setStatus(1);
		task.setStartTime(new Date());
		if(e!=null){
			task.setDescription("启动异常：" + StringUtils.substring(e.getMessage(), 0, 100));
		}
		this.taskInfoService.updateTaskStatus(task);
		this.recTaskLogs(taskInfo, TasklogType.Start, e);
	}

	@Override
	public void stop(cn.fetosoft.rooster.core.TaskInfo taskInfo, SchedulerException e) {
		TaskInfo task = new TaskInfo();
		task.setCode(taskInfo.getCode());
		task.setStatus(0);
		task.setStopTime(new Date());
		if(e!=null){
			task.setDescription("停止异常：" + StringUtils.substring(e.getMessage(), 0, 100));
		}
		this.taskInfoService.updateTaskStatus(task);
		this.recTaskLogs(taskInfo, TasklogType.Stop, e);
	}

	private void recTaskLogs(cn.fetosoft.rooster.core.TaskInfo taskInfo, TasklogType logType,
	                         SchedulerException e){
		TaskLogs logs = new TaskLogs();
		logs.setCode(taskInfo.getCode());
		logs.setName(taskInfo.getName());
		logs.setExpression(taskInfo.getExpression());
		logs.setHost(taskInfo.getClusterIP());
		logs.setType(logType.code());
		if(logType == TasklogType.Start) {
			logs.setStartTime(new Date());
		}else if(logType == TasklogType.Stop){
			logs.setEndTime(new Date());
		}
		logs.setCreateTime(new Date());
		logs.setStatus(Status.Success.code());
		if(e!=null){
			logs.setStatus(Status.Failure.code());
			logs.setErrorMsg(StringUtils.substring(e.getMessage(), 1, 100));
		}
		taskLogsService.insert(logs);
	}
}
