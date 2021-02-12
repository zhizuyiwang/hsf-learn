package com.hsf.learn.muxin;

import com.hsf.learn.muxin.netty.chart.WSServer;
import com.hsf.learn.muxin.netty.http.HttpServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyStarter implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			try {
				HttpServer.getInstance().start();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

			}
		}
	}
	
}
