package com.success.quartz;

import org.quartz.spi.JobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/8/24 8:58
 * @Description
 * @Version
 */
@Component("mySchedulerFactoryBean")
public class MySchedulerFactoryBean extends SchedulerFactoryBean{
    @Resource(name = "myJobFactory")
    @Override
    public void setJobFactory(JobFactory jobFactory) {
        super.setJobFactory(jobFactory);
    }
}
