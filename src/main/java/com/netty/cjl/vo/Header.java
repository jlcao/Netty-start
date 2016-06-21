package com.netty.cjl.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jlcao on 2016/6/20.
 */
public class Header {
    private int crcCode = 12;
    private int length;
    private long sessionID;
    private byte type;
    private byte priority;

    private Map<String, Object> attchment = new HashMap<String, Object>();

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionID=" + sessionID +
                ", type=" + type +
                ", priority=" + priority +
                ", attchment=" + attchment +
                '}';
    }

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttchment() {
        return attchment;
    }

    public void setAttchment(Map<String, Object> attchment) {
        this.attchment = attchment;
    }
}
