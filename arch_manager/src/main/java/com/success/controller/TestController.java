package com.success.controller;

import com.success.rabbitmq.MQSender;
import com.success.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/7/23 6:57
 * @Description
 * @Version
 */
@RequestMapping("/test")
@Controller
public class TestController {

    /*@Autowired
    private MQSender mqSender;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/mq")
    @ResponseBody
    public String testMq(@RequestParam String msg){
        mqSender.sendDictMessage(msg);
        return "mq:"+msg;
    }

    @RequestMapping("/redis")
    @ResponseBody
    public String testRedis(@RequestParam String msg){
        redisService.set("name",msg);
        return "redis"+msg;
    }*/

}
