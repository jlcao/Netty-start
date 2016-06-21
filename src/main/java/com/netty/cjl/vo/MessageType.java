package com.netty.cjl.vo;

/**
 * Created by cjl on 2016/6/21.
 */
public enum MessageType {
    LOGIN_REQ("登录认证", (byte)1),
    LOGIN_RES("登录响应", (byte)2),
    HEARTBEAT_REQ("心跳请求", (byte) 3),
    HEARTBEAT_RESP("心跳响应",(byte)4);


    String desc;
    byte value;

    MessageType(String str, byte b) {
        this.desc = str;
        this.value = b;
    }


    public byte value() {
        return value;
    }

}

