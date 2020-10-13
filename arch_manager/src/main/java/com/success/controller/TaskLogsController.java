package com.success.controller;

import com.alibaba.fastjson.JSON;

import com.success.common.Datagrid;
import com.success.form.TaskLogsForm;
import com.success.service.TaskLogsService;
import com.success.vo.TaskLogsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 定时日志查询
 * @author guobingbing
 * @create 2020-04-25 10:43
 */
@Controller
@RequestMapping("/task/taskLogs")
public class TaskLogsController {

	@Autowired
	private TaskLogsService taskLogsService;


	/**
	 * 查询定时任务日志
	 * @author guobingbing
	 * @date 2020-04-25 11:45
	 * @param form
	 * @return java.lang.String
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public String datagrid(@ModelAttribute TaskLogsForm form){
		Datagrid<TaskLogsVO> datagrid = new Datagrid<>();
		int total = taskLogsService.selectCountByForm(form);
		datagrid.setTotal(total);
		if(total>0){
			form.setDescField("id");
			List<TaskLogsVO> voList = taskLogsService.selectListVOByForm(form);
			datagrid.setRows(voList);
		}
		return JSON.toJSONString(datagrid);
	}
}
