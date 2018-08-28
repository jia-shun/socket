package org.js.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.js.entry.Person;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by JiaShun on 2018/7/14.
 */

public class PersonEncoder extends MessageToByteEncoder<Person> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Person msg, ByteBuf out) throws Exception {
       System.out.println("PersonEncoder -> encode action...");
       ByteArrayOutputStream byteOutputStream =  new ByteArrayOutputStream();
       ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
       objectOutputStream.writeObject(msg);
       byte[] bytes = byteOutputStream.toByteArray();
       out.writeBytes(bytes);
       ctx.flush();
    }
}
