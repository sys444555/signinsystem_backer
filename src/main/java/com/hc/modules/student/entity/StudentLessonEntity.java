package com.hc.modules.student.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import java.util.Date;
/**
 * @author ：fenghuang
 * @date ：Created in 2019/12/22 17:08
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class StudentLessonEntity {

    private Integer status;

    private Date signTime;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer gender;

    private String birth;

    private String guarderPhone;

    private Date createTime;

}
