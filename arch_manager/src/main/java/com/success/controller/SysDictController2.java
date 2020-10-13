package com.success.controller;


import com.success.bean.SysDict;
import com.success.common.CodeMsg;
import com.success.common.Result;
import com.success.config.GlobalConfig;
import com.success.rabbitmq.MQSender;
import com.success.service.SysDictService;
import com.success.zookeeper.ZkClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/dict2")
public class SysDictController2 {

    private static final Logger logger = LoggerFactory.getLogger(SysDictController2.class);

    /*@Autowired
    private SysDictService sysDictService;
    @Autowired
    private MQSender mqSender;


    @ResponseBody
    @RequestMapping(value = {"/list"})
    public List<SysDict> list(HttpServletRequest request, Model model){

        List<SysDict> sysDicts = sysDictService.selectAll();
        return sysDicts;
    }


    @RequestMapping("/create")
    @ResponseBody
    public Result create(@ModelAttribute SysDict sysDict){
        try {
            int flag = sysDictService.saveSysDict(sysDict);
            if(flag>0){
                mqSender.sendDictMessage(sysDict.getCategory());
                return Result.success(null);
            }
        } catch (Exception e) {
            logger.error("createDictError",e);
        }
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute SysDict sysDict){
        try {
            int flag = sysDictService.updateSysDict(sysDict);
            if(flag>0){
                mqSender.sendDictMessage(sysDict.getCategory());
                return Result.success(null);
            }
        } catch (Exception e) {
            logger.error("updateDictError",e);
        }
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        try {
            SysDict dict = sysDictService.selectById(id);
            int flag = sysDictService.deleteSysDict(id);
            if (flag > 0) {
                mqSender.sendDictMessage(dict.getCategory());
                return Result.success(null);
            }
        } catch (Exception e) {
            logger.error("deleteDictError",e);
        }
        return Result.error(CodeMsg.SERVER_ERROR);
    }*/
}
