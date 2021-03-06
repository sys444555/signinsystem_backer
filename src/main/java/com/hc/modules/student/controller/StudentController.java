package com.hc.modules.student.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.service.StudentService;


import com.hc.modules.teacher.entity.ClassEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 查看学生
     * @param sid
     * @return
     */
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

    /**
     * 查询改老师的学生列表
     */
    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    public ResponseUtil getClassStudentById(Integer pageNo, Integer pageSize, HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        PageHelper.startPage(pageNo, pageSize);
        List<StudentEntity> studentList = studentService.selectStudentList(token);
        PageInfo<StudentEntity> pageInfo = new PageInfo<>(studentList);
        return ResponseUtil.success(pageInfo);
    }

    /**
     * 查看学生所有课时包信息
     */
    @RequestMapping(value = "/student/get/coursePackage/{studentId}", method = RequestMethod.GET)
    public ResponseUtil getStudentCoursePackageList(@PathVariable(value = "studentId") Integer studentId){
        List<StudentEntity> studentCoursePackageList = studentService.getStudentCoursePackageList(studentId);
        return ResponseUtil.success(studentCoursePackageList);
    }

    /**
     * 删除学员录学员
     */
    @RequestMapping(value = "/student/delete/{studentId}", method = RequestMethod.POST)
    public ResponseUtil deleteStudent(@PathVariable(value = "studentId") Integer studentId){
        studentService.deleteStudent(studentId);
        return ResponseUtil.success();
    }

    /**
     * 修改学员信息
     */
    @RequestMapping(value = "/student/update", method = RequestMethod.POST)
    public ResponseUtil updateStudent(StudentEntity studentEntity){
        studentService.updateStudent(studentEntity);
        return ResponseUtil.success();
    }

}
