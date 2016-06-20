package com.netty.cjl.coder;

import com.netty.cjl.vo.NettyMessage;
import com.sun.xml.internal.ws.util.Pool;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

import java.util.List;

/**
 * Created by jlcao on 2016/6/20.
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {
    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() {
        marshallingEncoder = MarshallingCodeCFactory.buildMarshallingEncoder();
    }

    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage nettyMessage, List<Object> list) throws Exception {

    }
}
