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
public class TestCronJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(TestCronJob.class);


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("TestCronJob start");

        logger.info("TestCronJob end");
    }


}
