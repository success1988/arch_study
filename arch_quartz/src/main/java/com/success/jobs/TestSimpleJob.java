package com.success.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/7/30 17:19
 * @Description
 * @Version
 */
public class TestSimpleJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(TestSimpleJob.class);


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("TestSimpleJob start");

        logger.info("TestSimpleJob end");
    }


}
