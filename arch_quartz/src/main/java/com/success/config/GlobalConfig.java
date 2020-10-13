package com.success.config;

import com.success.component.DictCacheListener;
import com.success.zookeeper.ZkClientBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/8/20 14:11
 * @Description
 * @Version
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "global")
public class GlobalConfig{

    /**
     * zk主机地址
     */
    private String zkHost;

    /**
     * 字典变动路径
     */
    private String zkDictPath;


    @Bean
    public ZkClientBuilder createClientBuiler(){
        return ZkClientBuilder.builder(this.zkHost);
    }
    /**
     * 用于监听数据字典变动
     * @return
     */
    @Bean
    public DictCacheListener createCacheListener(){
        return new DictCacheListener(this.zkDictPath);
    }

}
