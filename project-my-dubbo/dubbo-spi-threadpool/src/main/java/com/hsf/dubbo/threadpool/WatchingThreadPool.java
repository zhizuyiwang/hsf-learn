package com.hsf.dubbo.threadpool;

import org.apache.dubbo.common.threadpool.support.fixed.FixedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WatchingThreadPool extends FixedThreadPool implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(WatchingThreadPool.class);
    private static final double ALARM_PERCENT = 0.90;
    public WatchingThreadPool(){

    }
    @Override
    public void run() {

    }
}
