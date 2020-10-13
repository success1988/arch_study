package com.success.mapper;

import com.success.bean.TaskLogs;
import com.success.form.TaskLogsForm;

import java.util.List;

public interface TaskLogsMapper extends BaseMapper<Integer, TaskLogs> {

	List<TaskLogs> selectListByForm(TaskLogsForm form);

	int selectCountByForm(TaskLogsForm form);
}
