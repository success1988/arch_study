package com.success.mapper;

import com.success.bean.TaskInfo;
import com.success.form.TaskInfoForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskInfoMapper extends BaseMapper<Integer, TaskInfo> {

	List<TaskInfo> selectListByForm(TaskInfoForm form);

	int selectCountByForm(TaskInfoForm form);

	int updateTaskStatus(TaskInfo taskInfo);

	/**
	 * 带有悲观锁的查询方法
	 * @param id 主键
	 * @return
	 */
	TaskInfo selectWithLock(Integer id);


	/**
	 * 将指定的记录由旧状态更新为新状态
	 * @param id 主键
	 * @param oldStatus 旧状态
	 * @param newStatus 新状态
	 * @return
	 */
	int updateStatusWithOld(@Param("id") Integer id , @Param("oldStatus")Integer oldStatus , @Param("newStatus")Integer newStatus);
}
