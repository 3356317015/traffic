package com.zhima.basic.jdbc;


public class PoolHandler {
	//数据库连接缓冲池
	public static PoolManager pool = new PoolManager(true);
	public void initPool(){
		pool = new PoolManager("proxool.xml");
	}
}
