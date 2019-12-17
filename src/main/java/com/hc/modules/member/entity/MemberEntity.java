package com.hc.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
@TableName("student")
public class MemberEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer gender;

    private String birth;

    private String guarder;

    private String guarderPhone;

    private String address;

    private Date createTime;

    private Integer status;

}
