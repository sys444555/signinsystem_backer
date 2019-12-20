package com.hc.modules.student.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("student")
public class StudentEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer gender;

    private String birth;

    private String guarder;

    private String guarderPhone;

    private String address;

    private Date createTime;
}
