package com.hc.modules.student.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.service.StudentService;


import com.hc.modules.teacher.entity.ClassEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 新增班学生
     */
    @RequestMapping(value = "/class/student/create", method = RequestMethod.POST)
    public ResponseUtil createStudent(StudentEntity studentEntity, Integer cid){
        studentService.createStudent(studentEntity, cid);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/student/getStudentById",method = RequestMethod.GET)
    public ResponseUtil getStudentById(Integer sid){

        return ResponseUtil.success(studentService.getStudentById(sid));
    }

    /**
     * 新增学生
     */
    @RequestMapping(value = "/student/insert", method = RequestMethod.POST)
    public ResponseUtil insertStudent(StudentEntity studentEntity, HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        studentService.insertStudent(studentEntity, token);
        return ResponseUtil.success();
    }

}
