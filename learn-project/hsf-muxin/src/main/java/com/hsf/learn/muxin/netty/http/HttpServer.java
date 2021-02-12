package com.hsf.learn.muxin.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;
@Component
public class HttpServer {
//    private static volatile HttpServer instance = null;
//    public synchronized static HttpServer getInstance(){
//        if(instance == null){
//            synchronized (HttpServer.class){
//                if(instance == null){
//                    instance = new HttpServer();
//                }
//            }
//        }
//        return instance;
//    }
    private static final class SingleHttpServer{
        static final HttpServer instance = new HttpServer();
    }
    public static HttpServer getInstance(){
        return SingleHttpServer.instance;
    }

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap bootstrap;
    private ChannelFuture future;
    private ChannelFuture closeFuture;
    private HttpServer(){
        bossGroup = new NioEventLoopGroup(1);
        workGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer());
    }
    public void start() {
        try{
            future = bootstrap.bind(8000).sync();
            System.err.println("netty http server 启动完毕...");
            closeFuture = future.channel().closeFuture().sync();
        }catch (Exception e){
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
