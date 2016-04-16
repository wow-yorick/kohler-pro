/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/

package com.kohler.framework.zookeeper.bean;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.kohler.framework.zookeeper.service.ZKListener;

/**
 * 
 * Zookeeper Factory
 *
 * @author kangmin_Qu
 * @Date 2014-9-26
 */
public class ZookeeperFactoryBean implements FactoryBean<CuratorFramework>, InitializingBean,
        DisposableBean {
    private static final Logger log = Logger.getLogger(ZookeeperFactoryBean.class);

    private CuratorFramework    zkClient;

    // 设置Zookeeper启动后需要调用的监听或者，或者需要做的初始化工作。
    public void setListeners(List<ZKListener> listeners) {
        this.listeners = listeners;
    }

    private List<ZKListener> listeners;

    // 设置ZK链接串
    public void setZkConnectionString(String zkConnectionString) {
        this.zkConnectionString = zkConnectionString;
    }

    private String zkConnectionString;

    public CuratorFramework getObject() {
        return zkClient;
    }

    public Class<?> getObjectType() {
        return CuratorFramework.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void destroy() throws Exception {
        zkClient.close();
    }

    // 创建ZK链接
    public void afterPropertiesSet() {
        // 1000 是重试间隔时间基数，3 是重试次数
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        zkClient = createWithOptions(zkConnectionString, retryPolicy, 2000, 10000);
        registerListeners(zkClient);
        zkClient.start();
    }

    /**
     * 通过自定义参数创建
     */
    public CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy,
            int connectionTimeoutMs, int sessionTimeoutMs) {

        CuratorFramework c = CuratorFrameworkFactory.builder().connectString(connectionString)
                .retryPolicy(retryPolicy).connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs).build();

        return c;
    }

    // 注册需要监听的监听者对像.
    private void registerListeners(CuratorFramework client) {
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                log.info("CuratorFramework state changed: {" + newState + "}");
                // if(newState == ConnectionState.CONNECTED || newState ==
                // ConnectionState.RECONNECTED){
                if (newState == ConnectionState.CONNECTED) {
                    for (ZKListener listener : listeners) {
                        try {
                            listener.executor(client);
                            log.info("Listener {" + listener.getClass().getName() + "} executed!");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }
            }
        });

        client.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {
            public void unhandledError(String message, Throwable e) {
                log.error("CuratorFramework unhandledError: {" + message + "}");
            }
        });
    }
}
