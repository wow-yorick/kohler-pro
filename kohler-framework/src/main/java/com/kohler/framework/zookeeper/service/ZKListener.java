/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.framework.zookeeper.service;

import org.apache.curator.framework.CuratorFramework;

/**
 * 
 * Zookeeper Listener Interface
 *
 * @author kangmin_Qu
 * @Date 2014-9-25
 */
public interface ZKListener {
    
    /**
     * Zookeeper Executor 
     * @param client
     * @author kangmin_Qu
     * Date 2014-9-25
     * @version
     */
    void executor(CuratorFramework client);
}
