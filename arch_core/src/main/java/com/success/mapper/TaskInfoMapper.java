package com.success.mapper;

import com.success.bean.TaskInfo;
import com.success.form.TaskInfoForm;

import java.util.List;

public interface TaskInfoMapper extends BaseMapper<Integer, TaskInfo> {

	List<TaskInfo> selectListByForm(TaskInfoForm form);

	int selectCountByForm(TaskInfoForm form);

	int updateTaskStatus(TaskInfo taskInfo);
}
