package com.hc.modules.lesson.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.hc.modules.student.entity.StudentEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@TableName("lesson")
public class LessonEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer classId;

    private BigDecimal classHour;

    private Integer status;

    private String startDate;

    private String endDate;

    private Date createTime;

    private Integer lessonNow;

    private List<StudentEntity> studentEntityList;

}
