package com.hsf.learn.common.netty;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * 系统初始化监听器
 */
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        NettyServer2 nettyServer2 = new NettyServer2(8021);
        try {
            nettyServer2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
