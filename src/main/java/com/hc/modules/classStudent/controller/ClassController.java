package com.hc.modules.classStudent.controller;

import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.classStudent.entity.ClassListVO;
import com.hc.modules.member.entity.MemberEntity;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hc.modules.classStudent.entity.ClassEntity;
import com.hc.modules.classStudent.service.ClassService;

import javax.servlet.http.HttpServletRequest;


/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
@RestController
@RequestMapping("class")
public class ClassController {
    @Autowired
    private ClassService classService;

    /**
     * 列表
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResponseUtil getMemberList(Integer pageNo, Integer pageSize, HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        PageHelper.startPage(pageNo, pageSize);
        List<ClassListVO> classList = classService.getClassList(token);
        PageInfo<ClassListVO> pageInfo = new PageInfo<>(classList);
        return ResponseUtil.success(pageInfo);
    }




}
