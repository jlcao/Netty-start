package com.netty.cjl.coder;

import com.netty.cjl.vo.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;
import java.util.Map;

/**
 * Created by jlcao on 2016/6/20.
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {
    private MyMarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() {
        this.marshallingEncoder = MarshallingCodeCFactory.buildMarshallingEncoder();
    }



    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage msg, List<Object> list) throws Exception {
        if (msg != null || msg.getHeader() != null) {
            ByteBuf sendBuf = Unpooled.buffer();
            sendBuf.writeInt(msg.getHeader().getCrcCode());
            sendBuf.writeInt(msg.getHeader().getLength());
            sendBuf.writeLong(msg.getHeader().getSessionID());
            sendBuf.writeByte(msg.getHeader().getType());
            sendBuf.writeByte(msg.getHeader().getPriority());
            sendBuf.writeInt(msg.getHeader().getAttchment().size());
            String key = null;
            byte[] keyArray = null;
            Object value = null;
            for (Map.Entry<String, Object> param : msg.getHeader().getAttchment().entrySet()) {
                key = param.getKey();
                keyArray = key.getBytes("UTF-8");
                sendBuf.writeInt(keyArray.length);
                sendBuf.writeBytes(keyArray);
                value = param.getValue();
                marshallingEncoder.encode(channelHandlerContext,value, sendBuf);
            }
            key = null;
            keyArray = null;
            value = null;
            if (msg.getBody() != null) {
                marshallingEncoder.encode(channelHandlerContext,msg.getBody(), sendBuf);
            }
            //将header中的length修改
            sendBuf.setInt(4, sendBuf.readableBytes());
            //把msg添加list传到下一个Handler
            list.add(sendBuf);
        }
    }


}
