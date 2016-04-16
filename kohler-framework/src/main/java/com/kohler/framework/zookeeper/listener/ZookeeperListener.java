/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.framework.zookeeper.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import com.kohler.bean.ZookeeperNodeInfo;
import com.kohler.constants.CommonConstants;
import com.kohler.framework.zookeeper.service.ZKListener;
import com.kohler.util.RedisUtils;

/**
 * 
 * @author kangmin_Qu
 * 
 */
public class ZookeeperListener implements ZKListener {
    private static final Logger log = Logger.getLogger(ZookeeperListener.class);

    private String              parentPath;

    // ZNode
    public ZookeeperListener(String parentPath) {
        this.parentPath = parentPath;
    }

    private ZookeeperNodeInfo convertJSONToObject(String JSON) throws JsonParseException,
            JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ZookeeperNodeInfo info = mapper.readValue(JSON, ZookeeperNodeInfo.class);//
        return info;
    }

    // 将zookeeper的路由数据保存到cache中，并创建redisPool也保存到Cache中
    private void saveNodeInfoToCache(List<ChildData> childDataList) throws JsonParseException,
            JsonMappingException, IOException {
        List<ZookeeperNodeInfo> infoList = new ArrayList<ZookeeperNodeInfo>();
        ZookeeperNodeInfo nodeInfo = null;
        for (ChildData childData : childDataList) {
            byte[] data = childData.getData();
            String sData = new String(data);
            log.info("------PathChildrenCacheListener----data:" + sData);
            nodeInfo = convertJSONToObject(sData);
            // 保存节点数据
            infoList.add(nodeInfo);
        }
        log.info("-------List<ZookeeperNodeInfo> infoList.size():" + infoList.size());
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();

        if (infoList != null && infoList.size() > 0) {

            // 定义Redis 连接池的参数
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(150);
            config.setMaxIdle(100);
            config.setMinIdle(50);
            config.setMaxWaitMillis(new Long(1000));
            config.setTestOnBorrow(true);

            for (ZookeeperNodeInfo n : infoList) {
                // 定义分片
                JedisShardInfo si = new JedisShardInfo(n.getRedisIP(), n.getRedisPort());
                shards.add(si);
            }

            RedisUtils.pool = new ShardedJedisPool(config, shards, Hashing.MURMUR_HASH,
                    Sharded.DEFAULT_KEY_TAG_PATTERN);
        }

    }

    public void executor(CuratorFramework client) {
        // TODO Auto-generated method stub
        // 创建PathChildrenCache用于监控节点变化包括节点和数据
        final PathChildrenCache pathCache = new PathChildrenCache(client, parentPath, true);
        log.info("-------new PathChildrenCache--------");
        // 添加监听器监听
        pathCache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent arg1)
                    throws Exception {
                // 节点出现变化，获取节点数据
                List<ChildData> childDataList = pathCache.getCurrentData();
                log.info("-------------childDataList:" + childDataList.size());
                // 保存节点数据到cache
                saveNodeInfoToCache(childDataList);
            }
        });
        try {
            pathCache.start();
        } catch (Exception e) {
            try {
                pathCache.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                log.error("pathCache close error:" + e1.getMessage());
            }
            log.error("Start pathCache error for path: {" + parentPath + "}, error info: {"
                    + e.getMessage() + "}");
        }

    }

}
