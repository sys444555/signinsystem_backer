package com.hc.modules.teacher.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/12/18 15:34
 * @description：
 * @modified By：
 * @version:
 */
@TableName("class")
@Data
public class TeacherEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer gender;

    private Integer userId;

}
