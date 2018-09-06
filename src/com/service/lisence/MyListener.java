package com.service.lisence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zhima.basic.jdbc.PoolHandler;

public class MyListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unused")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		PoolHandler poolHandler = new PoolHandler();
	}

}
