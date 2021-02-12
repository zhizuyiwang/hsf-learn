package com.hsf.learn.muxin.netty.chart;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class WSServer {
	private static final class SingleWSServer{
		static final WSServer instance = new WSServer();
	}
	public static WSServer getInstance(){
		return SingleWSServer.instance;
	}
	
	private EventLoopGroup mainGroup;
	private EventLoopGroup subGroup;
	private ServerBootstrap server;
	private ChannelFuture future;
	private ChannelFuture closeFuture;

	private WSServer() {
		mainGroup = new NioEventLoopGroup();
		subGroup = new NioEventLoopGroup();
		server = new ServerBootstrap();
		server.group(mainGroup, subGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new WSServerInitializer());
	}

	public void start(){
		try {
			this.future = server.bind(8001).sync();
			System.err.println("netty websocket server 启动完毕...");
			this.closeFuture = future.channel().closeFuture().sync();
		}catch (InterruptedException exception){
			mainGroup.shutdownGracefully();
			subGroup.shutdownGracefully();
		}finally {
			mainGroup.shutdownGracefully();
			subGroup.shutdownGracefully();
		}

	}
}
