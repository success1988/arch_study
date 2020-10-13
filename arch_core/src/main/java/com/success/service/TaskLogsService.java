package com.success.service;

import com.success.bean.TaskLogs;
import com.success.form.TaskLogsForm;
import com.success.vo.TaskLogsVO;

import java.util.List;

/**
 * @Title：任务运行日志
 */
public interface TaskLogsService  extends BaseDataService<Integer, TaskLogs> {

	/**
	 * 查询数据
	 */
	List<TaskLogs> selectListByForm(TaskLogsForm form);

	/**
	 * 查询记录数
	 */
	int selectCountByForm(TaskLogsForm form);

	/**
	 * 查询列表
	 */
	List<TaskLogsVO> selectListVOByForm(TaskLogsForm form);
}
