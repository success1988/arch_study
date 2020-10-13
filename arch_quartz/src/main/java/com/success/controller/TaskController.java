package com.success.controller;

import com.success.bean.TaskInfo;
import com.success.common.CodeMsg;
import com.success.common.Result;
import com.success.form.TaskInfoForm;
import com.success.service.SchedulerService;
import com.success.service.TaskInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/8/24 8:36
 * @Description
 * @Version
 */
@CrossOrigin
@Controller
@RequestMapping("/task")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskInfoService taskInfoService;

    @Autowired
    private SchedulerService schedulerService;


    @RequestMapping("/start")
    @ResponseBody
    public Result start(@RequestParam("code")String code){
        try {
            TaskInfoForm taskInfoForm = new TaskInfoForm();
            taskInfoForm.setCode(code);
            List<TaskInfo> taskInfos = taskInfoService.selectListByForm(taskInfoForm);
            if(CollectionUtils.isEmpty(taskInfos)){
                return Result.error(CodeMsg.SERVER_ERROR);
            }
            TaskInfo taskInfo = taskInfos.get(0);

            return schedulerService.start(taskInfo);
        } catch (Exception e) {
            logger.error("start Task Error",e);
        }
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/stop")
    @ResponseBody
    public Result stop(@RequestParam("code")String code){
        try {
            TaskInfoForm taskInfoForm = new TaskInfoForm();
            taskInfoForm.setCode(code);
            List<TaskInfo> taskInfos = taskInfoService.selectListByForm(taskInfoForm);
            if(CollectionUtils.isEmpty(taskInfos)){
                return Result.error(CodeMsg.SERVER_ERROR);
            }
            TaskInfo taskInfo = taskInfos.get(0);

            return schedulerService.stop(taskInfo);
        } catch (Exception e) {
            logger.error("start Task Error",e);
        }
        return Result.error(CodeMsg.SERVER_ERROR);
    }
}
