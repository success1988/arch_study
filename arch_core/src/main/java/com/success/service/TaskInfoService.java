package com.success.service;

import com.success.bean.TaskInfo;
import com.success.form.TaskInfoForm;
import com.success.vo.TaskInfoVO;

import java.util.List;

/**
 * 定时任务
 */
public interface TaskInfoService extends BaseDataService<Integer, TaskInfo> {

	/**
	 * 查询数据列表
	 */
	List<TaskInfo> selectListByForm(TaskInfoForm form);

	/**
	 * 查询数据记录数
	 */
	int selectCountByForm(TaskInfoForm form);

	/**
	 * 查询列表
	 */
	List<TaskInfoVO> selectListVoByForm(TaskInfoForm form);

	/**
	 * 更新定时器状态
	 */
	int updateTaskStatus(TaskInfo taskInfo);
}
