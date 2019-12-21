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

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseStartDate = null;
        try {
            parseStartDate = simpleDateFormat.parse("2019-12-13 01:01:01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(parseStartDate);
        c1.add(Calendar.DAY_OF_MONTH, 1);
        parseStartDate = c1.getTime();
        String startDate = simpleDateFormat.format(parseStartDate);
        System.out.println("startDate = " + startDate);
    }
}
