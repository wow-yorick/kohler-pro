/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.bean;

/**
 * 
 * @author kangmin_Qu
 *
 */
public class ZookeeperNodeInfo {
	private String serverName;
	private String childNodeName;
	private String policy;
	private int weight;
	private String redisIP;
	private int redisPort;
	private int requestTimeout;
	private int maxWait;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getChildNodeName() {
		return childNodeName;
	}

	public void setChildNodeName(String childNodeName) {
		this.childNodeName = childNodeName;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getRedisIP() {
		return redisIP;
	}

	public void setRedisIP(String redisIP) {
		this.redisIP = redisIP;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

	public int getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(int requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}
}
