package com.hc.modules.teacher.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import lombok.Data;
import java.util.Date;
/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
@TableName("class")
@Data
public class ClassEntity  extends TeacherEntity{


	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
		/**
	 * 班名称
	 */
	private String className;
		/**
	 *
	 */
	private String courseName;
		/**
	 * 0课时/0.5课时/1课时/1.5课时/2课时/2.5课时/3课时/3.5课时/4课时/4.5课时/5课时/5.5课时/6课时/6.5课时/7课时/7.5课时/8课时
	 */
	private BigDecimal classHour;
		/**
	 * 老师id
	 */
	private Integer tId;
		/**
	 *
	 */
	private Date createTime;
		/**
	 * 0禁用 1启用
	 */
	private Integer status;


}