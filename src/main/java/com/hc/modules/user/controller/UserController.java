package com.hc.modules.user.controller;


import com.jckc_backer.model.ResultMap;
import com.jckc_backer.modules.reg.service.RegService;
import com.jckc_backer.modules.user.entity.RoleUserEntity;
import com.jckc_backer.modules.user.mapper.RoleUserMapper;
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

    @Resource
    private RegService regService;

    @RequestMapping(value="/reg",method= RequestMethod.POST)
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions("vip")
    public ResultMap getVipReg(RoleUserEntity regEntity) {
        System.out.println(regEntity);
        regService.reg(regEntity);
        return resultMap.success().code(200).message("注册成功");
    }
}
