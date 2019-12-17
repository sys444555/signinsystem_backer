package com.hc.common.entityEnum.client;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/5/28 11:09
 * @description：
 * @modified By：
 * @version:
 */

public enum LinkGender {

    male (0, "男"),
    female (1, "女");


    public final Integer index;

    public final String gender;

    private LinkGender(Integer index , String gerder){
        this.index = index;
        this.gender = gerder;
    }

    public Integer getIndex(){
        return this.index;
    }

    public String getGender(){
        return this.gender;
    }
}
