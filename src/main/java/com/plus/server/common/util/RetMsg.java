package com.plus.server.common.util;

/**
 * Created by yutao on 2016/1/7.
 */
public class RetMsg {
    private String code;
    private String msg;

    public RetMsg(String code, String msg) {
        this.code = code;
        if(msg == null){
            msg = "";
        }
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RetMsg)) {
            return false;
        }

        RetMsg retMsg = (RetMsg) obj;
        if (retMsg.getCode() == null || retMsg.getMsg() == null) {
            return false;
        }
        if (retMsg.getCode().equals(this.getCode()) && retMsg.getMsg().equals(this.getMsg())) {
            return true;
        }
        return false;
    }

}
