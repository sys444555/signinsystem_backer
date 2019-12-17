package com.hc.common.exception;



/**
 * @author ：fenghuang
 * @date ：Created in 2019/3/11 18:41
 * @description：自定义异常处理类
 * @modified By：
 * @version:
 */
public class JcException extends
    RuntimeException{
    private static final long serialVersionUID = 1L;

    private String msg;

    private int code = 500;



    public JcException(String msg) {
        super(msg);
        this.msg = msg;
    }


    public JcException(Throwable e, String msg) {
        super(msg,e);
        this.msg = msg;
    }

    public JcException(int code,String msg ){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public JcException(int code,String msg,Throwable e ){
        super(msg,e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
