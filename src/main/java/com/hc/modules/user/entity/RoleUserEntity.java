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
 * @date 2019-08-26 16:29:25
 */
@TableName("role_user")
@Data
public class RoleUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 角色
	 */
	private String role;
	/**
	 * 权限
	 */
	private String permission;
	/**
	 * 封号状态：1：以封号 ，0：标示：未封号
	 */
	private Integer ban;
}


