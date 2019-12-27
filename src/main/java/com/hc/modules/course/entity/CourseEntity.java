package com.hc.modules.course.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.teacher.entity.TeacherEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@TableName("course")
public class CourseEntity   {


    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer userId;

    private Date createTime;

}
