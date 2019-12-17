package com.hc.modules.user.controller;


import com.hc.model.ResultMap;
import com.hc.modules.user.entity.RoleUserEntity;
import com.hc.modules.user.mapper.RoleUserMapper;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 *
 * @Author
 * @Description user角色权限controller
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private RoleUserMapper userMapper;

    @Resource
    private ResultMap resultMap;

}
