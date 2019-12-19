package com.hc.modules.course.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
@TableName("course")
public class CourseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer classId;


}
