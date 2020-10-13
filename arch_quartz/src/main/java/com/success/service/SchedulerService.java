package com.success.service;

import com.success.bean.TaskInfo;
import com.success.common.Result;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/8/24 8:47
 * @Description
 * @Version
 */
public interface SchedulerService {


    /**
     * 开启任务
     * @param taskInfo
     * @return
     */
    public Result start(TaskInfo taskInfo);
    /**
     * 停止任务
     * @param taskInfo
     * @return
     */
    public Result stop(TaskInfo taskInfo);
    /**
     * 变更并重启任务
     * @param taskInfo
     * @return
     */
    public Result modify(TaskInfo taskInfo);
}
