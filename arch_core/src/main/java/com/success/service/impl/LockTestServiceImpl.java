package com.success.service.impl;

import com.alibaba.fastjson.JSON;
import com.success.bean.TaskInfo;
import com.success.bean.TaskLogs;
import com.success.mapper.TaskInfoMapper;
import com.success.mapper.TaskLogsMapper;
import com.success.service.LockTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/10/15 7:14
 * @Description
 * @Version
 */
@Service
public class LockTestServiceImpl implements LockTestService {

    private static final Logger logger = LoggerFactory.getLogger(LockTestServiceImpl.class);

    @Autowired
    private TaskInfoMapper taskInfoMapper;
    @Autowired
    private TaskLogsMapper taskLogsMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateInfoAndInsertLog(int infoId, int targetStatus) {
        int resultRow = 0;
        TaskInfo taskInfo = taskInfoMapper.selectByPrimaryKey(infoId);

        logger.info("当前线程为:"+Thread.currentThread().getName()+",查询结果为:"+ JSON.toJSONString(taskInfo));

        //1.更新taskInfo的状态
        int row = taskInfoMapper.updateStatusWithOld(infoId, taskInfo.getStatus(), targetStatus);

        logger.info("当前线程为:"+Thread.currentThread().getName()+"，更新结果为:"+row);
        //用休眠模拟其他业务操作
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //2.若更新taskInfo的状态成功，则向taskLogs中插入一条记录,否则直接抛出异常
        if(row > 0){
            TaskLogs taskLogs = new TaskLogs();
            taskLogs.setCode(taskInfo.getCode());
            taskLogs.setCreateTime(new Date());
            taskLogs.setRemark(Thread.currentThread().getName());
            resultRow = taskLogsMapper.insert(taskLogs);
        }else{
            throw new RuntimeException("当前线程为:"+Thread.currentThread().getName()+",更新taskInfo的状态失败，请稍后重试");
        }


        return resultRow;
    }
}
