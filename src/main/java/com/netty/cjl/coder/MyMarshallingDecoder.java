package com.netty.cjl.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

/**
 * Created by cjl on 2016/6/21.
 */
public class MyMarshallingDecoder extends MarshallingDecoder {
    public MyMarshallingDecoder(UnmarshallerProvider provider) {
        super(provider);
    }


    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        return super.decode(ctx, in);
    }
}
