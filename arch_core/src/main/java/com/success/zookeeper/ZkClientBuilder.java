package com.success.zookeeper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PreDestroy;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.server.ZooTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkClientBuilder {
    private static final Logger logger = LoggerFactory.getLogger(ZkClientBuilder.class);
    private static final String DATA_CHARSET = "utf-8";
    private static final ThreadLocal<String> PATH_LOCAL = new ThreadLocal();
    private static final Map<String, NodeCache> CACHE_MAP = new HashMap(8);
    private CuratorFramework client;

    private ZkClientBuilder(String host) {
        logger.info(host);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.newClient(host, retryPolicy);
        this.client.start();
        logger.info("Curator is start--------");
    }

    public static ZkClientBuilder builder(String host) {
        return new ZkClientBuilder(host);
    }

    public ZkClientBuilder createNode(CreateMode mode, String path) throws Exception {
        if (this.client == null) {
            throw new Exception("The curator is null!");
        } else {
            if (this.client.checkExists().forPath(path) == null) {
                ((ACLBackgroundPathAndBytesable)this.client.create().creatingParentsIfNeeded().withMode(mode)).forPath(path);
            }

            PATH_LOCAL.set(path);
            return this;
        }
    }

    public boolean writeData(String data) throws Exception {
        String path = (String)PATH_LOCAL.get();
        return this.writeData(path, data);
    }

    public boolean writeData(String path, String data) throws Exception {
        if (this.checkPath(path)) {
            if (StringUtils.isBlank(data)) {
                throw new Exception("The data is null or empty!");
            }

            this.client.setData().forPath(path, data.getBytes("utf-8"));
        }

        return true;
    }

    public String getNodeData(String path) throws Exception {
        return this.checkPath(path) ? new String((byte[])this.client.getData().forPath(path), "utf-8") : null;
    }

    private boolean checkPath(String path) throws Exception {
        if (this.client == null) {
            throw new Exception("The curator is null!");
        } else if (this.client.checkExists().forPath(path) == null) {
            throw new Exception("The node of '" + path + "' is null!");
        } else {
            return true;
        }
    }

    public NodeCache createNodeCache(String path) {
        NodeCache nodeCache = null;
        if (CACHE_MAP.get(path) == null) {
            nodeCache = new NodeCache(this.client, path, false);
            CACHE_MAP.put(path, nodeCache);
        }

        return nodeCache;
    }

    private void closeClient() {
        Iterator iter = CACHE_MAP.entrySet().iterator();

        try {
            while(iter.hasNext()) {
                Entry<String, NodeCache> entry = (Entry)iter.next();
                if (entry.getValue() != null) {
                    ((NodeCache)entry.getValue()).close();
                }
            }
        } catch (IOException var3) {
            logger.error("closeCurator", var3);
        }

        if (this.client != null) {
            ZooTrace.logTraceMessage(logger, ZooTrace.getTextTraceLevel(), "Run shutdown now.");
            this.client.close();
            logger.info("Curator is closed--------");
        }

    }

    @PreDestroy
    public void destroy() {
        this.closeClient();
    }
}
