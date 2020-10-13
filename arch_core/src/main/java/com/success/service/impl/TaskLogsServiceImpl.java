package com.success.service.impl;

import com.success.bean.TaskInfo;
import com.success.bean.TaskLogs;
import com.success.enums.DateFormatEnum;
import com.success.enums.Status;
import com.success.enums.TasklogType;
import com.success.form.TaskLogsForm;
import com.success.mapper.BaseMapper;
import com.success.mapper.TaskLogsMapper;
import com.success.service.AbstractDataService;
import com.success.service.TaskLogsService;
import com.success.vo.TaskLogsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title：任务运行日志
 */
@Service
public class TaskLogsServiceImpl  extends AbstractDataService<Integer, TaskLogs> implements TaskLogsService {

	@Autowired
	private TaskLogsMapper taskLogsMapper;


	/**
	 * 查询数据
	 */
	@Override
	public List<TaskLogs> selectListByForm(TaskLogsForm form) {
		return taskLogsMapper.selectListByForm(form);
	}

	/**
	 * 查询记录数
	 */
	@Override
	public int selectCountByForm(TaskLogsForm form) {
		return taskLogsMapper.selectCountByForm(form);
	}

	/**
	 * 查询列表
	 */
	@Override
	public List<TaskLogsVO> selectListVOByForm(TaskLogsForm form) {
		List<TaskLogsVO> voList = new ArrayList<>();
		List<TaskLogs> list = this.taskLogsMapper.selectListByForm(form);
		if(!CollectionUtils.isEmpty(list)){
			for(TaskLogs logs : list){
				TaskLogsVO vo = new TaskLogsVO();
				BeanUtils.copyProperties(logs, vo);
				vo.setTypeText(TasklogType.getDesc(logs.getType()));
				vo.setStatusText(Status.getStatus(logs.getStatus()));
				if(logs.getCreateTime()!=null){
					vo.setCreateTime(DateFormatEnum.YMD_HMS.dateToString(logs.getCreateTime()));
				}
				if(logs.getStartTime()!=null){
					vo.setStartTime(DateFormatEnum.YMD_HMS.dateToString(logs.getStartTime()));
				}
				if(logs.getEndTime()!=null){
					vo.setEndTime(DateFormatEnum.YMD_HMS.dateToString(logs.getEndTime()));
				}
				voList.add(vo);
			}
		}
		return voList;
	}

    @Override
    protected BaseMapper<Integer, TaskLogs> getBaseMapper() {
        return taskLogsMapper;
    }
}
