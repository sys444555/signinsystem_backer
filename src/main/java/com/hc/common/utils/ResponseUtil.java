package com.hc.common.utils;

import java.util.HashMap;


/**
 * @author ：fenghuang
 * @date ：Created in 2019/3/11 17:23
 * @description： 结果返回类
 * @modified By：
 * @version:
 *
/**
 * 响应操作结果
 *
 *  code： 返回码
 *  msg：  返回消息
 *  data： 响应数据
 *
 */
public class ResponseUtil extends HashMap<String, Object>{

    public ResponseUtil(){
        put("code", 0);
        put("msg", "success");
    }
    public static ResponseUtil success(){
        ResponseUtil res = new ResponseUtil();
        res.put("code", 0);
        res.put("msg", "success");
        return res;
    }

    public static ResponseUtil success(String msg){
        ResponseUtil res = new ResponseUtil();
        res.put("code", 0);
        res.put("msg", msg);
        return res;
    }

    public static ResponseUtil success(Object data){
        ResponseUtil res = new ResponseUtil();
        res.put("code", 0);
        res.put("msg", "success");
        res.put("data",data);
        return res;
    }

    public static ResponseUtil success(String msg, Object data){
        ResponseUtil res = new ResponseUtil();
        res.put("code", 0);
        res.put("msg", msg);
        res.put("data", data);
        return res;
    }

    public static ResponseUtil error(){
        return error(HttpResponseStatusUtil.SC_INTERNAL_SERVER_ERROR, "服务器未知异常，请联系工作人员");
    }

    public static ResponseUtil error(String msg){
        ResponseUtil res = new ResponseUtil();
        res.put("code", HttpResponseStatusUtil.SC_INTERNAL_SERVER_ERROR);
        res.put("msg", msg);
        return res;
    }

    public static ResponseUtil error(int code, String msg){
        ResponseUtil res = new ResponseUtil();
        res.put("code", code);
        res.put("msg", msg);
        return res;
    }
}
