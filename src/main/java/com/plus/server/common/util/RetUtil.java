package com.plus.server.common.util;



import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;




/**
 * Created by yutao on 2016/1/5.
 */

/**
 * 工具类，用于生成返回给app的Json对象
 */
public class RetUtil {

    /**
     * 生成包含returnCode = 0000,returnMsg=成功，data为空的基础Json对象
     * @return
     */
    public static JSONObject createBaseRetJsonObj(){
        return createBaseRetJsonObj(null);
    }

    /**
     * 生成包含returnCode = retMsg.code,returnMsg=retMsg.msg，data为空的基础Json对象
     * @param retMsg json返回值:code,msg
     * @return
     */
    public static JSONObject createBaseRetJsonObj(String retMsg){
        if (retMsg == null){
            retMsg = "success";
        }
        JSONObject retJobj = new JSONObject();
        retJobj.put("returnCode","0000");
        retJobj.put("returnMsg","success");
        retJobj.put("data", new JSONObject());
        return retJobj;
    }



    public static void writeJson(HttpServletResponse servletResponse, String ret) {
        try {
            servletResponse.setContentType("text/html;charset=UTF-8");
            servletResponse.getWriter().append(ret);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String writeJson(String ret) {
        return ret;
    }

    public static String retMsgToJsonString(String retMsg) {
        JSONObject jObj = RetUtil.createBaseRetJsonObj(retMsg);
        return jObj.toString();
    }

}
