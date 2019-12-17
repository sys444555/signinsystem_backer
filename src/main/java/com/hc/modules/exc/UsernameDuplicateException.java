package com.hc.modules.exc;

public class UsernameDuplicateException extends ServiceException{

    public UsernameDuplicateException() {
    }

    public UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UsernameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicateException(String message) {
        super(message);
    }

    public UsernameDuplicateException(Throwable cause) {
        super(cause);
    }
}
