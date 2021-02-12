package com.hsf.learn.common.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NettyServer4 {
    private  int port =8080;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public NettyServer4(int port) {
        this.port = port;
    }

      public void start() throws InterruptedException {
          NioEventLoopGroup boss = new NioEventLoopGroup();
          NioEventLoopGroup work = new NioEventLoopGroup();
          ServerBootstrap b = new ServerBootstrap();
          b.group(boss,work)
                  .channel(NioServerSocketChannel.class)
                  .localAddress(new InetSocketAddress(port))
                  .childHandler(new ChannelInitializer<SocketChannel>() {
                      @Override
                      protected void initChannel(SocketChannel ch) throws Exception {
                          ch.pipeline().addLast(new EchoServerHandler());
                      }
                  });

          System.out.println("启动加载netty4");

          ChannelFuture channelFuture = b.bind().sync();
          if(channelFuture.isSuccess()){
              System.out.println("启动成功");
          }
      }
}
