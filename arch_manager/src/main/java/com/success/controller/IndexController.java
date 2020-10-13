package com.success.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title：后台管理页面跳转
 * @Author：wangchenggong
 * @Date 2020/7/11 16:16
 * @Description
 * @Version
 */
@Controller
public class IndexController {

    //访问后台管理的首页及子菜单
    @RequestMapping(value = {"/","/{page}","/{page}/{detail}"})
    public String toPage(@PathVariable(value = "page",required = false)String page
            , @PathVariable(value = "detail",required = false)String detail){
        if(page == null){
            return "index";
        }else if(detail == null){
            return page;
        }else{
            return page+"/"+detail;
        }
    }


}
