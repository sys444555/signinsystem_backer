package com.hc.modules.student.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class StudentEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer gender;

    private String birth;


    private String guarderPhone;


    private Date createTime;

    private Integer teacherId;
}
