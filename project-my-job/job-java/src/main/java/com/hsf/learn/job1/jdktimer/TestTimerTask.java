package com.hsf.learn.job1.jdktimer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * @Author: qingshan
 * @Date: 2019/9/3 22:31
 * @Description: 咕泡学院，只为更好的你
 */
public class TestTimerTask extends TimerTask {
    /**
     * 此计时器任务要执行的操作。
     */
    public void run() {
        Date executeTime = new Date(this.scheduledExecutionTime());
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("任务执行了：" + dateStr);
    }
}
