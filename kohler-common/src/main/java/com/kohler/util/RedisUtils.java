/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * RedisUtil Function Description
 * 
 * @author kangmin_Qu
 * @Date 2014-11-18
 */
@Component("redisUtils")
public class RedisUtils {
    private static final Logger log = Logger.getLogger(RedisUtils.class);

    /**
     * Redis pool
     */
    public static ShardedJedisPool pool;
    
    
    /**
     * Object transfer to Byte[]
     * @param obj
     * @return
     * @author kangmin_Qu
     * Date 2014-12-8
     * @version
     */
    public byte[] objectToByte(Object obj) {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            // object to bytearray
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();
        } catch (Exception e) {
            log.error("Redis ObjectToByte Error : ",e);
        }finally{
            if(bo != null){
                try {
                    bo.close();
                } catch (IOException e) {
                }
            }
            if(oo != null){
                try {
                    oo.close();
                } catch (IOException e) {
                }
            }
        }
        return (bytes);
    }


    /**
     *  Byte[] transfer to Object
     * @param bytes
     * @return
     * @author kangmin_Qu
     * Date 2014-12-8
     * @version
     */
    private Object byteToObject(byte[] bytes) {
        Object obj = new Object();
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            // bytearray to object
            bi = new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (Exception e) {
            log.error("Redis ByteToObject Error : ", e);
        } finally {
            if (bi != null) {
                try {
                    bi.close();
                } catch (IOException e) {
                }
            }
            if (oi != null) {
                try {
                    oi.close();
                } catch (IOException e) {
                }
            }
        }
        return obj;
    }
}
