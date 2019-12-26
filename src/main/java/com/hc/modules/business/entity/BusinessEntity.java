package com.hc.modules.business.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
@TableName("business")
public class BusinessEntity {


    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String phone;

    private Integer msnBuyNumber;

    private Integer msnLeftNumber;

    private String companyName;

    private String username;

    private String password;

    private Integer userId;

    private Date createTime;

}
