package org.js.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.js.server.handler.PersonDecoder;
import org.js.server.handler.PersonServerHandler;

/**
 * Created by JiaShun on 2018/7/11.
 */

public class NettyServer {
    private int port;
    public NettyServer(int port){
        this.port = port;
    }

    public void action() {
        ServerBootstrap server = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup(5);
        server.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .localAddress("localhost",port)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new PersonDecoder());
                        ch.pipeline().addLast(new PersonServerHandler());
                    }
                });
        try {
            ChannelFuture future = server.bind().sync();
            System.out.println("NettyServer -> 执行完成，服务启动成功！");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
