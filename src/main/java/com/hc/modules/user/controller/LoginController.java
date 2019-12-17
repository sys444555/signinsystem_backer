package com.hc.modules.user.controller;

import com.hc.common.utils.JWTUtil;
import com.hc.model.ResultMap;
import com.hc.modules.user.mapper.RoleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author
 * @Description
 * @Date
 * @Time
 */
@RestController
public class LoginController {

    @Autowired
    private JWTUtil jwtUtil;

    @Resource
    private RoleUserMapper userMapper;

    @Resource
    private ResultMap resultMap;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultMap login(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           HttpServletRequest request) {
        request.getSession().setAttribute("uid", 1);
        String realPassword = userMapper.getPassword(username);
        if (realPassword == null) {
            return resultMap.fail().code(401).message("用户名错误");
        } else if (!realPassword.equals(password)) {
            return resultMap.fail().code(401).message("密码错误");
        } else {

            String token=jwtUtil.createToken(username);

            return resultMap.success().code(200).message(token);
        }
    }


}
