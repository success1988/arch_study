package com.success.controller;

import com.success.service.LockTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title：验证乐观锁+悲观锁
 * @Author：wangchenggong
 * @Date 2020/10/15 7:01
 * @Description
 * @Version
 */
@RestController
@RequestMapping("/lock")
public class LockController {


    @Autowired
    public LockTestService lockTestService;

    @RequestMapping("/test")
        public int testLock(@RequestParam("id")Integer id, @RequestParam("targetStatus")Integer targetStatus){

        int resultRow = lockTestService.updateInfoAndInsertLog(id,targetStatus);
        return resultRow;
    }

}
