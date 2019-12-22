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

    private String guarder;

    private String guarderPhone;

    private String address;

    private Date createTime;

    public static void main(String[] args) throws ParseException {
        String startDate = "2019-12-31 17:00:00";
        String[] s = startDate.split(" ");
        System.out.println("s[1] = " + s[1]);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
        String format1 = simpleDateFormat2.format(simpleDateFormat2.parse(startDate.split(" ")[1]));
        System.out.println("format1 = " + format1);
    }
}
