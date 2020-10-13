package com.success.controller;

import cn.fetosoft.rooster.broadcast.TaskBroadcast;
import cn.fetosoft.rooster.core.TaskAction;
import cn.fetosoft.rooster.core.TaskException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.success.bean.TaskInfo;
import com.success.common.Datagrid;
import com.success.form.Result;
import com.success.form.TaskInfoForm;
import com.success.service.TaskInfoService;
import com.success.vo.TaskInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 定时任务
 */
@Controller
@RequestMapping("/task/taskInfo")
public class TaskInfoController {

	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private TaskBroadcast taskBroadcast;



	/**
	 * 查询注册的机器
	 */
	@RequestMapping("/registClusters")
	@ResponseBody
	public String getRegistClusters(){
		StringBuilder sb = new StringBuilder();
		List<String> clusters = taskBroadcast.getRegisterdClusters();
		for(String s : clusters){
			sb.append(s).append(", ");
		}
		return sb.toString();
	}

	/**
	 * 查询
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public String datagrid(@ModelAttribute TaskInfoForm form){
		Datagrid<TaskInfoVO> datagrid = new Datagrid<>();
		int total = taskInfoService.selectCountByForm(form);
		datagrid.setTotal(total);
		if(total>0){
			form.setDescField("id");
			List<TaskInfoVO> voList = taskInfoService.selectListVoByForm(form);
			datagrid.setRows(voList);
		}
		return JSON.toJSONString(datagrid);
	}

	/**
	 * 新增
	 */
	@RequestMapping("/create")
	@ResponseBody
	public Result create(@ModelAttribute TaskInfo taskInfo){
		Result result = Result.errorResult();
		taskInfo.setStatus(0);
		taskInfo.setCreateTime(new Date());
		int flag = taskInfoService.insert(taskInfo);
		if(flag>0){
			result.setCode(Result.SUCCESS);
		}
		return result;
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(@ModelAttribute TaskInfo taskInfo){
		Result result = Result.errorResult();
		int flag = taskInfoService.updateByPrimaryKeySelective(taskInfo);
		if(flag>0){
			result.setCode(Result.SUCCESS);
		}
		return result;
	}

	/**
	 * 删除任务
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam int id){
		Result result = Result.errorResult();
		TaskInfo task = taskInfoService.selectByPrimaryKey(id);
		if(task!=null) {
			if(task.getStatus()==0){
				int flag = taskInfoService.deleteByPrimaryKey(id);
				if (flag > 0) {
					result.setCode(Result.SUCCESS);
				}
			}else {
				result.setMsg("任务运行中");
			}
		}else{
			result.setMsg("任务不存在");
		}
		return result;
	}

	/**
	 * 启动
	 */
	@RequestMapping("/start")
	@ResponseBody
	public Result start(@RequestParam int id){
		Result result = Result.errorResult();
		TaskInfo task = taskInfoService.selectByPrimaryKey(id);
		if(task!=null){
			cn.fetosoft.rooster.core.TaskInfo roosterTask = this.buildRoosterTask(task);
			try {
				cn.fetosoft.rooster.core.Result r = taskBroadcast.broadcast(roosterTask);
				if(r== cn.fetosoft.rooster.core.Result.SUCCESS){
					result.setCode(Result.SUCCESS);
				}else{
					result.setMsg(r.getMsg());
				}
			} catch (TaskException e) {
				result.setMsg(e.getMessage());
			}
		}else{
			result.setMsg("任务不存在");
		}
		return result;
	}

	/**
	 * 停止任务
	 */
	@RequestMapping("/stop")
	@ResponseBody
	public Result stop(@RequestParam int id){
		Result result = Result.errorResult();
		TaskInfo task = taskInfoService.selectByPrimaryKey(id);
		if(task!=null){
			cn.fetosoft.rooster.core.TaskInfo roosterTask = this.buildRoosterTask(task);
			try {
				roosterTask.setAction(TaskAction.STOP.getCode());
				cn.fetosoft.rooster.core.Result r = taskBroadcast.broadcast(roosterTask);
				if(r== cn.fetosoft.rooster.core.Result.SUCCESS){
					result.setCode(Result.SUCCESS);
				}else{
					result.setMsg(r.getMsg());
				}
			} catch (TaskException e) {
				result.setMsg(e.getMessage());
			}
		}else{
			result.setMsg("任务不存在");
		}
		return result;
	}

	private cn.fetosoft.rooster.core.TaskInfo buildRoosterTask(TaskInfo task){
		cn.fetosoft.rooster.core.TaskInfo roosterTask = new cn.fetosoft.rooster.core.TaskInfo();
		roosterTask.setCode(task.getCode());
		roosterTask.setExpression(task.getExpression());
		roosterTask.setClusterIP(task.getClusterip());
		roosterTask.setJobClass(task.getJobclass());
		roosterTask.setName(task.getName());
		roosterTask.setAction(TaskAction.START.getCode());
		roosterTask.setDescription(task.getDescription());
		if(StringUtils.isNotBlank(task.getParams())) {
			JSONObject json = JSON.parseObject(task.getParams());
			roosterTask.addAllParams(json);
		}
		return roosterTask;
	}

}
