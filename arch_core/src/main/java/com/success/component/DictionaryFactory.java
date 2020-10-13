package com.success.component;

import com.success.bean.SysDict;
import com.success.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/7/12 22:53
 * @Description
 * @Version
 */
@Component
public class DictionaryFactory implements ApplicationListener<DictEvent> {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryFactory.class);

    private static final Map<String, Map<String, String>> DICT_MAP = new HashMap<>(32);
    private static SysDictService sysDictService;

    @PostConstruct
    public void initDict(){
        this.loadAllDictData();
    }

    private void loadAllDictData(){
        long start = System.currentTimeMillis();
        List<SysDict> dictList = sysDictService.selectAll();
        if(!CollectionUtils.isEmpty(dictList)){
            for(SysDict lopDict : dictList){
                Map<String, String> map = DICT_MAP.get(lopDict.getCategory());
                if(map==null){
                    map = new HashMap<>(8);
                }
                map.put(lopDict.getName(), lopDict.getValue());
                DICT_MAP.put(lopDict.getCategory(), map);
            }
        }

        logger.info("Load all dict success, the items is {} that cost time {}ms.", dictList.size(), (System.currentTimeMillis() - start));
    }

    /**
     * 根据类型获取字典Map
     * @param category
     * @return
     */
    private static Map<String, String> getDict(String category, boolean isReload){
        Map<String, String> map = DICT_MAP.get(category);
        if(map==null || isReload) {
            List<SysDict> sysDictList = sysDictService.selectByCategory(category);
            map = sysDictList.stream().collect(Collectors.toMap(SysDict::getName, SysDict::getValue));
            DICT_MAP.put(category, map);
        }
        return map;
    }


    public void onApplicationEvent(DictEvent event) {
        Map<String, String> map = getDict(event.getCategory(), true);
        int size = map!=null?map.size():0;
        logger.info("Reload the dict named {} success, the items is {}.", event.getCategory(), size);
    }

    @Autowired
    public void setSysDictService(SysDictService sysDictService) {
        DictionaryFactory.sysDictService = sysDictService;
    }



    public enum Dict{

        /**
         * 婚姻状况
         */
        Marriage("marriage"),
        /**
         * 子女状况
         */
        kidCount("kidCount"),
        /**
         * 住房情况
         */
        HouseCondition("houseCondition"),
        /**
         * 教育背景
         */
        EducationBackground("educationBackground"),
        ;


        private String catagory;

        Dict(String catagory){
            this.catagory = catagory;
        }

        public String getCatagory(){
            return this.catagory;
        }

        public Map<String, String> getDictMap() {
            return DictionaryFactory.getDict(this.getCatagory(), false);
        }
    }

}
