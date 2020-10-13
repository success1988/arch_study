package com.success.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.success.component.DictionaryFactory;
import com.success.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.Map;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/7/14 6:48
 * @Description
 * @Version
 */
//@Controller
public class MainController2 {

   /* @Autowired
    private RedisService redisService;


    @RequestMapping("/sys/dict2")
    @ResponseBody
    public String getDictionaryJSON(@RequestParam String dictType){

        Map<String, String> map = redisService.getHashValue(dictType);
        String result = "";
        if(map!=null && !map.isEmpty()){
            JSONArray jsonArray = new JSONArray();
            Iterator iter = map.keySet().iterator();
            while (iter.hasNext()){
                Object key = iter.next();
                String value = map.get(key).toString();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("key", value);
                jsonObject.put("value", key.toString());
                jsonArray.add(jsonObject);
            }
            result = jsonArray.toJSONString();
        }
        return result;
    }*/

}
