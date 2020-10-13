package com.success;

import cn.fetosoft.rooster.core.ScheduledService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"com.success","cn.fetosoft.rooster"})
@MapperScan(basePackages = "com.success.mapper")
public class TimerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TimerApplication.class, args);

        ScheduledService scheduledService = context.getBean(ScheduledService.class);
        System.out.println(scheduledService);
    }
}
