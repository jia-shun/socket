package org.js.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.js.entry.Person;

/**
 * Created by JiaShun on 2018/7/14.
 */

public class PersonClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * 客户端和服务端连接建立完成
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("PersonClientHandler -> channelActive 连接完成，准备发送数据给服务器！");
        Person person = new Person();
        person.setAge(18);
        person.setName("Michael");
        person.setSex(0);
        ctx.write(person);
        ctx.flush();
    }

    /**
     * 从服务器读到数据的时候会被调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("PersonClientHandler -> channelRead0接到服务器的数据，准备读取！");
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String responseStr = new String(bytes,"utf-8");
        System.out.println("收到服务端返回的数据: "+responseStr);
    }


}
