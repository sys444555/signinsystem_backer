package com.hc.common.exception;


import com.hc.common.utils.ResponseUtil;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author ：fenghuang
 * @date ：Created in 2019/3/11 18:54
 * @description：统一异常处理类
 * @modified By：
 * @version:
 */

@RestControllerAdvice
public class JcExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(JcExceptionHandler.class);


/**
     * 处理自定义异常
     * @param
     * @return
     */

    @ExceptionHandler(JcException.class)
    public ResponseUtil handleJcException(JcException e){
        System.out.println("e = " + e);
        logger.error(e.getMsg(),e);
        return ResponseUtil.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseUtil handleAuthenticationException(AuthenticationException e){
        System.out.println("hello");
        return ResponseUtil.error(403, e.getMessage());

    }


    //处理其他异常类

/**
     * 处理RuntimeException
     * @param
     * @return
     */

    @ExceptionHandler(RuntimeException.class)
    public ResponseUtil handleRuntimeException(Exception e){
        System.out.println("fea = ");
        logger.error(e.getMessage(),e);
        return ResponseUtil.error(500, "服务器端出现异常");
    }

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public ResponseUtil handle401(ShiroException e) {
        System.out.println("no");
        return ResponseUtil.error(401, e.getMessage());
    }




/**
     * 处理未知异常
     * @param
     * @return
     */

    @ExceptionHandler(Exception.class)
    public ResponseUtil handleException(Exception e){
        logger.error(e.getMessage(),e);
        return ResponseUtil.error(-1, "未知错误");
    }


}

