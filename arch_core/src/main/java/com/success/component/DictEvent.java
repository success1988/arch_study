package com.success.component;

import org.springframework.context.ApplicationEvent;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/7/12 22:44
 * @Description
 * @Version
 */
public class DictEvent  extends ApplicationEvent {

    private String category;

    /**
     *
     */
    public DictEvent(String category) {
        super(category);
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }
}
