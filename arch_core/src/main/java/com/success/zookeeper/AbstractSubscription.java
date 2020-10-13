package com.success.zookeeper;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/7/12 20:09
 * @Description
 * @Version
 */
public abstract class AbstractSubscription {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSubscription.class);
    @Resource
    private ZkClientBuilder clientBuilder;

    public AbstractSubscription() {
    }

    @PostConstruct
    public void subscription() throws Exception {
        final String path = this.createNode();
        if (StringUtils.isNotBlank(path)) {
            NodeCache nodeCache = this.clientBuilder.createNodeCache(path);
            nodeCache.start();
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                public void nodeChanged() {
                    try {
                        String data = AbstractSubscription.this.clientBuilder.getNodeData(path);
                        AbstractSubscription.LOGGER.info("nodeChanged >> {}", data);
                        AbstractSubscription.this.doNodeChanged(data);
                    } catch (Exception var2) {
                        AbstractSubscription.LOGGER.error("nodeChanged >> " + path, var2);
                    }

                }
            });
            LOGGER.info("The listener is start forPath '{}'.", path);
        } else {
            LOGGER.info("The path is null or empty!");
        }

    }

    protected abstract String createNode();

    protected abstract void doNodeChanged(String var1);
}
