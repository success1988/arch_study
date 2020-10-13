package com.success.component;

import com.success.zookeeper.AbstractSubscription;
import com.success.zookeeper.ZkClientBuilder;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/7/12 22:41
 * @Description
 * @Version
 */
public class DictCacheListener extends AbstractSubscription implements ApplicationContextAware {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(DictCacheListener.class);
    private ApplicationContext applicationContext;
    @Autowired
    private ZkClientBuilder clientBuilder;
    /**
     * 订阅path
     */
    private String subPath;

    public DictCacheListener(String subPath){
        this.subPath = subPath;
    }

    /**
     * 创建监听节点
     * @author gbinb
     * @date 2020-03-27 10:03
     * @param
     * @return java.lang.String
     */
    @Override
    protected String createNode() {
        try{
            clientBuilder.createNode(CreateMode.PERSISTENT, subPath);
            return subPath;
        }catch(Exception e){
            logger.error("createNode", e);
        }
        return null;
    }

    @Override
    protected void doNodeChanged(String s) {
        logger.info("Listener：{}", s);
        this.applicationContext.publishEvent(new DictEvent(s));
    }

    public void  setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        this.applicationContext = applicationContext;
    }
}