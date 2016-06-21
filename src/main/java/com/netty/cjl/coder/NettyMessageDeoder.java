package com.netty.cjl.coder;

import com.netty.cjl.vo.Header;
import com.netty.cjl.vo.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cjl on 2016/6/21.
 */
public class NettyMessageDeoder extends LengthFieldBasedFrameDecoder {
    MyMarshallingDecoder decoder;

    /**
     *
     * @param maxFrameLength  消息的最大长度
     * @param lengthFieldOffset   长度字段的位置
     * @param lengthFieldLength   长度字段的长度
     */
    public NettyMessageDeoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        decoder = MarshallingCodeCFactory.buildMarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

       /* ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }*/

        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionID(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());

        int size = in.readInt();
        if (size > 0) {
            Map<String, Object> attch = new HashMap<String, Object>(size);
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i = 0; i < size; i++) {
                keySize = in.readInt();
                keyArray = new byte[keySize];
                in.readBytes(keyArray);
                key = new String(keyArray, "utf-8");
                attch.put(key, decoder.decode(ctx, in));
            }
            keyArray = null;
            key = null;
            header.setAttchment(attch);
        }
        if (in.readableBytes()>0) {
            message.setBody(decoder.decode(ctx, in));
        }
        message.setHeader(header);
        return message;
    }
}
