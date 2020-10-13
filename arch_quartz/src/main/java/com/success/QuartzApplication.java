package com.success;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @Title：Quartz定时任务api用法
 * @Author：wangchenggong
 * @Date 2020/8/20 13:52
 * @Description
 * @Version
 */
@SpringBootApplication(scanBasePackages = {"com.success"})
@ComponentScan(excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.success.rabbitmq.*")
                    ,@ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.success.redis.*")
                })
@MapperScan(basePackages = "com.success.mapper")
public class QuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

}
