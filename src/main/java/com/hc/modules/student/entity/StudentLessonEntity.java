package com.hc.modules.student.entity;

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
public class StudentLessonEntity extends StudentEntity{

    private Integer status;

    private Date signTime;

}
