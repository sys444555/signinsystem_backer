package com.hc.common.utils;

public final class TextValidator {

    public TextValidator(){
        super();
    }

    /**
     * 手机号码正则表达式
     */
    public static final String REGEX_PHONE = "((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}";
    /**
     * 邮箱正则表达式
     */
    public static final String REGEX_EMAIL	=	"^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /**
     * 姓名正则表达式
     */
    public static final String REGEX_NAME = "^[\\u4e00-\\u9fa5]+(·[\\u4e00-\\u9fa5]+)*$";
    /**
     * 职业正则表达式
     */
    public static final String REGEX_OCCUPATION = "^[\\u4e00-\\u9fa5]+(·[\\u4e00-\\u9fa5]+)*$";
    /**
     * 信息正则表达式
     */
    public static final String REGEX_MESSAGE = "^[\\u4e00-\\u9fa5]+(·[\\u4e00-\\u9fa5]+)*$";

    public static boolean checkPhone(String phone){
        if(phone == null){
            return false;
        }
        return phone.matches(REGEX_PHONE);
    }

    public static boolean checkEmail(String email){
        if(email == null){
            return false;
        }
        return email.matches(REGEX_EMAIL);
    }

    public static boolean checkName(String name){
        return name.matches(REGEX_NAME);
    }

    public static boolean checkOccupation(String occupation){
        return occupation.matches(REGEX_OCCUPATION);
    }

    public static boolean checkMessage(String message){
        return message.matches(REGEX_MESSAGE);
    }
}
