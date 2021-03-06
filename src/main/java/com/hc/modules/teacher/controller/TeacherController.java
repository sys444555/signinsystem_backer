package com.hc.modules.teacher.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.teacher.entity.ClassEntity;
import com.hc.modules.teacher.entity.TeacherEntity;
import com.hc.modules.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询班级列表
     */
    @RequestMapping(value = "/teacher/list",method = RequestMethod.GET)
    public ResponseUtil getClassList(){
        List<TeacherEntity> teacherList = teacherService.getTeacherList();
        return ResponseUtil.success(teacherList);
    }


    /**
     * 查询班级列表
     */
    @RequestMapping(value = "/class/list",method = RequestMethod.GET)
    public ResponseUtil getClassList(Integer pageNo, Integer pageSize, HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        PageHelper.startPage(pageNo, pageSize);
        List<ClassEntity> classList = teacherService.getClassList(token);
        PageInfo<ClassEntity> pageInfo = new PageInfo<>(classList);
        return ResponseUtil.success(pageInfo);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/class/create", method = RequestMethod.POST)
    public ResponseUtil createClass(ClassEntity classEntity){
        teacherService.insertClass(classEntity);
        return ResponseUtil.success();
    }

    /**
     * 修改班状态
     */
    @RequestMapping(value = "/class/update/{status}/{id}", method = RequestMethod.POST)
    public ResponseUtil updateClassStatus(@PathVariable(value = "id") Integer id, @PathVariable(value = "status") Integer status){
        teacherService.updateClassStatus(status, id);
        return ResponseUtil.success();
    }

    /**
     * 查看班级学生详情
     */
    @RequestMapping(value = "/class/getStudent/{cid}", method = RequestMethod.GET)
    public ResponseUtil getClassStudentById(Integer pageNo, Integer pageSize,@PathVariable(value = "cid") Integer cid){
        PageHelper.startPage(pageNo, pageSize);
        List<StudentEntity> studentList = teacherService.getClassStudentById(cid);
        PageInfo<StudentEntity> pageInfo = new PageInfo<>(studentList);
        return ResponseUtil.success(pageInfo);
    }

    /**
     * 查看班级课时详情
     */
    @RequestMapping(value = "/class/getCourse/{cid}", method = RequestMethod.GET)
    public ResponseUtil getClassCourseById(Integer pageNo, Integer pageSize, @PathVariable(value = "cid") Integer cid){
        PageHelper.startPage(pageNo, pageSize);
        List<CourseEntity> courseList = teacherService.getClassCourseById(cid);
        PageInfo<CourseEntity> pageInfo = new PageInfo<>(courseList);
        return ResponseUtil.success(pageInfo);
    }

    /**
     * 新增班学生
     */
    @RequestMapping(value = "/class/students/create", method = RequestMethod.POST)
    public ResponseUtil insertClassStudents(Integer classId, String[] studentList){
        teacherService.insertClassStudents(classId, studentList);
        return ResponseUtil.success();
    }

    /**
     * 查看未加入该课节的学生录
     */
    @RequestMapping(value = "/class/student/absent/list/{classId}",method = RequestMethod.GET)
    public ResponseUtil getLessonAbsentStudentList(@PathVariable(value = "classId") Integer classId, HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        List<StudentEntity> studentEntityList = teacherService.getClassAbsentStudentList(classId, token);
        return ResponseUtil.success(studentEntityList);
    }

    /**
     * 删除班
     */
    @RequestMapping(value = "/class/delete/{classId}", method = RequestMethod.POST)
    public ResponseUtil deleteClass(@PathVariable(value = "classId") Integer classId){
        teacherService.deleteClass(classId);
        return ResponseUtil.success();
    }

    /**
     * 根据id获取班级的信息
     */
    @RequestMapping(value = "/class/getClassById/{classId}", method = RequestMethod.POST)
    public ResponseUtil getClassById(@PathVariable(value = "classId") Integer classId){
        ClassEntity classEntity  = teacherService.getClassById(classId);
        return ResponseUtil.success(classEntity);
    }

    /**
     * 根据id修改班信息
     */
    @RequestMapping(value = "/class/update", method = RequestMethod.POST)
    public ResponseUtil updateClass(ClassEntity classEntity){
        teacherService.updateClass(classEntity);
        return ResponseUtil.success();
    }

}
