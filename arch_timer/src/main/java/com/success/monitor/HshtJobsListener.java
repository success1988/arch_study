package com.success.monitor;

import cn.fetosoft.rooster.core.TaskInfo;
import cn.fetosoft.rooster.monitor.JobContext;
import cn.fetosoft.rooster.monitor.JobExecListener;
import com.success.bean.TaskLogs;
import com.success.enums.Status;
import com.success.enums.TasklogType;
import com.success.service.TaskLogsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Job监控
 * @author guo
 * @create 2020-03-26 17:54
 */
@Component
public class HshtJobsListener implements JobExecListener {

	@Autowired
	private TaskLogsService taskLogsService;

	@Override
	public void beforeExec(JobContext jobContext) {
	}

	@Override
	public void afterExec(JobContext jobContext) {
		TaskInfo taskInfo = jobContext.getTaskInfo();
		TaskLogs logs = new TaskLogs();
		logs.setCode(taskInfo.getCode());
		logs.setName(taskInfo.getName());
		logs.setExpression(taskInfo.getExpression());
		logs.setType(TasklogType.Running.code());
		logs.setHost(taskInfo.getClusterIP());
		logs.setStartTime(jobContext.getFireTime());
		logs.setEndTime(new Date());
		logs.setExecTime(jobContext.getRunTime());
		logs.setCreateTime(new Date());
		logs.setStatus(Status.Success.code());
		if(jobContext.isException()){
			logs.setStatus(Status.Failure.code());
			logs.setErrorMsg(StringUtils.substring(jobContext.getErrorMsg(),0,100));
		}

		taskLogsService.insert(logs);
	}
}
