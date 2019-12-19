package com.hc.modules.student.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.service.StudentService;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResponseUtil getStudentList(Integer pageNo, Integer pageSize, HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        PageHelper.startPage(pageNo, pageSize);
        List<StudentEntity> studentList = studentService.getStudentList(token);
        PageInfo<StudentEntity> pageInfo = new PageInfo<>(studentList);
        return ResponseUtil.success(pageInfo);
    }
}
