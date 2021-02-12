package com.hsf.learn.common.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
public class NettyServer1 {
//      @PostConstruct
      public void start() throws InterruptedException {
          System.out.println("记载启动服务器1");
          NioEventLoopGroup boss = new NioEventLoopGroup();
          NioEventLoopGroup work = new NioEventLoopGroup();
          ServerBootstrap b = new ServerBootstrap();
          b.group(boss,work)
                  .channel(NioServerSocketChannel.class)
                  .localAddress(new InetSocketAddress(8020))
                  .childHandler(new ChannelInitializer<SocketChannel>() {
                      @Override
                      protected void initChannel(SocketChannel ch) throws Exception {
                          ch.pipeline().addLast(new EchoServerHandler());
                      }
                  });

          System.out.println("启动加载netty1");

          ChannelFuture channelFuture = b.bind().sync();
          if(channelFuture.isSuccess()){
              System.out.println("netty1启动成功==port: 8020");
          }
      }
//      @PreDestroy
      public void destroyNetty(){
          System.out.println("销魂netty");
      }

}
