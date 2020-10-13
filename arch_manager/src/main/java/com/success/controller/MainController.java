package com.success.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.success.bean.SysDict;
import com.success.component.DictionaryFactory;
import com.success.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/7/14 6:48
 * @Description
 * @Version
 */
@Controller
public class MainController {

    @Autowired
    private SysDictService sysDictService;

    @RequestMapping("/sys/dict")
    @ResponseBody
    public String getDictionaryJSON(@RequestParam String dictType){
        String result = "";

        List<SysDict> sysDictList = sysDictService.selectByCategory(dictType);
        Map map = sysDictList.stream().collect(Collectors.toMap(SysDict::getName, SysDict::getValue));

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
    }








    @RequestMapping("/sys/dict2")
    @ResponseBody
    public String getDictionaryJSON2(@RequestParam String dictType){
        DictionaryFactory.Dict[] values = DictionaryFactory.Dict.values();
        Map map = null;
        for(DictionaryFactory.Dict dict:values){
            if(dictType.equals(dict.getCatagory())){
                map = dict.getDictMap();
                break;
            }
        }
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
    }

}
