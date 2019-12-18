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

@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 新增
     */
    @RequestMapping(value = "/student/create", method = RequestMethod.POST)
    public ResponseUtil createStudent(StudentEntity studentEntity, Integer cid){
        studentService.insertStudent(studentEntity, cid);
        return ResponseUtil.success();
    }
}
