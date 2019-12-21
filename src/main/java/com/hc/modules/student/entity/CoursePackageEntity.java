package com.hc.modules.student.entity;



import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 课时包表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-20 17:32:45
 */
@TableName("course_package")
@Data
public class CoursePackageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
@TableId(type = IdType.AUTO)
	private Integer id;
		/**
	 * 课包
	 */
private String classPackage;
		/**
	 * 收费标准
	 */
private String chargingStandard;
		/**
	 * 购买总课时
	 */
private BigDecimal buyClassHour;
		/**
	 * 已耗课时
	 */
private BigDecimal consumedClassHour;
		/**
	 * 是否在有效期  0不在  1在
	 */
private Integer isValidity;
		/**
	 * 有效期到  年月日
	 */
private String periodOfValidity;
		/**
	 * 价格
	 */
private BigDecimal price;
		/**
	 *
	 */
private Date createTime;
		/**
	 * 学生id
	 */
private Integer studentId;
	/**
	 * 已耗课时
	 */
	private BigDecimal leftClassHour;

}
