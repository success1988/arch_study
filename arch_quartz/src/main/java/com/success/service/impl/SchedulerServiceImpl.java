package com.success.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.success.bean.TaskInfo;
import com.success.common.CodeMsg;
import com.success.common.Result;
import com.success.service.SchedulerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/8/24 8:50
 * @Description
 * @Version
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);
    private static String JOB_GROUP_NAME = "My_JOB_GROUP";
    private static String TRIGGER_GROUP_NAME = "MY_TRIGGER_GROUP";

    /**
     * quartz提供的用来和调度器交互的API
     */
    @Resource(name = "mySchedulerFactoryBean")
    private Scheduler scheduler;


    @Override
    public Result start(TaskInfo taskInfo) {

        try {
            //你希望被调度的任务体
            Class jobClass = Class.forName(taskInfo.getJobclass());

            //用来定义任务实例
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(taskInfo.getCode(), JOB_GROUP_NAME).build();
            //设置任务参数信息，可以通过JobExecutionContext获取
            JSONObject json = JSON.parseObject(taskInfo.getParams());
            jobDetail.getJobDataMap().putAll(json);


            //定义Job运行的调度时间表
            Trigger trigger = buildSimpleOrCronTrigger(taskInfo);

            Date date = scheduler.scheduleJob(jobDetail, trigger);
            if(!scheduler.isShutdown()){
                scheduler.start();
                logger.info("The task start success >>>{} >>>{}", taskInfo.getCode(), taskInfo.getName());
                return Result.success(date);
            }
        } catch (ClassNotFoundException | SchedulerException | ParseException e) {
            logger.error("job start throw Exception, taskInfo:{}", taskInfo.toString() ,e);
        }
        return  Result.error(CodeMsg.SERVER_ERROR);
    }


    /**
     * 根据任务信息构建不同的触发器
     * @param taskInfo 任务信息
     * @return
     * @throws ParseException
     */
    private Trigger buildSimpleOrCronTrigger(TaskInfo taskInfo) throws ParseException {
        Trigger trigger;
        String expression = taskInfo.getExpression();
        if(expression.startsWith("{") && expression.endsWith("}")){
            //SimpleTrigger
            JSONObject simpleTriggerInfo = JSONObject.parseObject(expression);
            String startTime = simpleTriggerInfo.getString("startTime");
            String endTime = simpleTriggerInfo.getString("endTime");
            int intervalSeconds = simpleTriggerInfo.getIntValue("intervalSeconds");
            //默认执行1次，即重复次数为0次
            int repeatCount = Objects.isNull(simpleTriggerInfo.get("repeatCount"))?0:simpleTriggerInfo.getIntValue("repeatCount");
            Date endDate = StringUtils.isBlank(endTime)? null : DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss");

            TriggerBuilder triggerBuilder = TriggerBuilder.newTrigger().withIdentity(taskInfo.getCode(), TRIGGER_GROUP_NAME);
            if(StringUtils.isBlank(startTime)){
                triggerBuilder.startNow();
            }else{
                triggerBuilder.startAt(DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm:ss"));
            }
            if(endDate != null){
                triggerBuilder.endAt(endDate);
            }
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(intervalSeconds)
                    .withRepeatCount(repeatCount);
            trigger = triggerBuilder.withSchedule(scheduleBuilder).build();
        }else{
            //CronTrigger
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskInfo.getExpression());
            trigger = TriggerBuilder.newTrigger().withIdentity(taskInfo.getCode(), TRIGGER_GROUP_NAME)
                    .withSchedule(scheduleBuilder).build();
        }
        return trigger;
    }

    @Override
    public Result stop(TaskInfo taskInfo) {
        Result result = Result.error(CodeMsg.SERVER_ERROR);
        try{
            logger.info("Ready to stop task >>>{} >>>{}", taskInfo.getCode(), taskInfo.getName());
            TriggerKey triggerKey = TriggerKey.triggerKey(taskInfo.getCode(), TRIGGER_GROUP_NAME);
            //停止触发器
            scheduler.pauseTrigger(triggerKey);
            //移除触发器
            scheduler.unscheduleJob(triggerKey);
            //移除job
            boolean flag = scheduler.deleteJob(JobKey.jobKey(taskInfo.getCode(), JOB_GROUP_NAME));

            logger.info("The task stop success >>>{} >>>{} ", taskInfo.getCode(), taskInfo.getName());
            return Result.success(flag);
        }catch(Exception e){
            result.setMsg(e.getMessage());
            logger.error("stop", e);
        }
        return result;
    }

    @Override
    public Result modify(TaskInfo taskInfo) {
        return null;
    }


}
