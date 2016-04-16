/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.kohler.service.DataCacheService;
import com.kohler.util.RedisUtils;

/**
 * data cache implements
 *
 * @author Administrator
 * @Date 2015年1月8日
 */
@Component
public class DataCacheServiceImpl implements DataCacheService {
    
    public static ShardedJedisPool pool;
    
    static {
        if(pool == null) {
            pool = RedisUtils.pool;
        }
        if(pool == null) {
            // 定义Redis 连接池的参数
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(150);
            config.setMaxIdle(100);
            config.setMinIdle(50);
            config.setMaxWaitMillis(new Long(1000));
            config.setTestOnBorrow(true);
            // slave链接 
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
            shards.add(new JedisShardInfo("192.168.2.14",2181));
            shards.add(new JedisShardInfo("192.168.2.15",2181));

            // 构造池 
            pool = new ShardedJedisPool(config, shards); 
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String hget(String key, String field) {
        ShardedJedis source = pool.getResource();
        return source.hget(key, field);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hset(String key, String field, String value) {
        ShardedJedis source = pool.getResource();
        source.hset(key, field, value);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hdelAll(String key) {
        try {
            ShardedJedis source = pool.getResource();
            source.del(key);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> hgetAll(String key) {
        ShardedJedis source = pool.getResource();
        return source.hgetAll(key);
    }

}
