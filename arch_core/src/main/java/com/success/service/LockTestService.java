package com.success.service;

/**
 * @Title：锁测试服务
 * @Author：wangchenggong
 * @Date 2020/10/15 7:10
 * @Description
 * @Version
 */
public interface LockTestService {

    /**
     * 更新任务信息的状态，同时插入日志
     * @param infoId 任务信息id
     * @param targetStatus 任务信息的目标状态
     * @return 任务日志的影响行数
     */
    int updateInfoAndInsertLog(int infoId,int targetStatus);
}
