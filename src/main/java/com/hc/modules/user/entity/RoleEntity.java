package com.hc.modules.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author fenghuang
 * @email
 * @date 2019-08-26 16:29:26
 */
@TableName("role")
@Data
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 角色
	 */
	private String role;
	/**
	 * 权限
	 */
	private String permission;
}


