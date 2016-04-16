/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.framework.zookeeper.bean;

import java.io.Serializable;

public class IPaddressListVO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String            ipaddress;

    private String            port;

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
