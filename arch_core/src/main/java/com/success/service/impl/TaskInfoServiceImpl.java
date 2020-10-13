package com.success.service.impl;

import com.success.bean.TaskInfo;
import com.success.enums.DateFormatEnum;
import com.success.form.TaskInfoForm;
import com.success.mapper.BaseMapper;
import com.success.mapper.TaskInfoMapper;
import com.success.service.AbstractDataService;
import com.success.service.BaseDataService;
import com.success.service.TaskInfoService;
import com.success.vo.TaskInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务管理
 */
@Service
public class TaskInfoServiceImpl extends AbstractDataService<Integer, TaskInfo> implements TaskInfoService {

	@Autowired
	private TaskInfoMapper taskInfoMapper;


	/**
	 * 查询数据列表
	 */
	@Override
	public List<TaskInfo> selectListByForm(TaskInfoForm form) {
		return taskInfoMapper.selectListByForm(form);
	}

	/**
	 * 查询数据记录数
	 */
	@Override
	public int selectCountByForm(TaskInfoForm form) {
		return taskInfoMapper.selectCountByForm(form);
	}

	/**
	 * 查询列表
	 */
	@Override
	public List<TaskInfoVO> selectListVoByForm(TaskInfoForm form) {
		List<TaskInfoVO> voList = new ArrayList<>();
		List<TaskInfo> list = taskInfoMapper.selectListByForm(form);
		if(!CollectionUtils.isEmpty(list)){
			for(TaskInfo task : list){
				TaskInfoVO vo = new TaskInfoVO();
				BeanUtils.copyProperties(task, vo);
				vo.setStatusName(task.getStatus()==1?"运行":"停止");
				vo.setCreateTime(DateFormatEnum.YMD_HMS.dateToString(task.getCreateTime()));
				vo.setStartTime(DateFormatEnum.YMD_HMS.dateToString(task.getStartTime()));
				vo.setStopTime(DateFormatEnum.YMD_HMS.dateToString(task.getStopTime()));
				voList.add(vo);
			}
		}
		return voList;
	}

	/**
	 * 更新定时器状态
	 */
	@Override
	public int updateTaskStatus(TaskInfo taskInfo) {
		return taskInfoMapper.updateTaskStatus(taskInfo);
	}

	@Override
	protected BaseMapper<Integer, TaskInfo> getBaseMapper() {
		return taskInfoMapper;
	}
}
