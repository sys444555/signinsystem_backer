package com.hc.modules.lesson.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.lesson.service.LessonService;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.teacher.entity.ClassEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LessonController {

    @Resource
    private LessonService lessonService;

    /**
     * 新增课节
     */
    @RequestMapping(value = "/class/lesson/create", method = RequestMethod.POST)
    public ResponseUtil createLesson(LessonEntity lessonEntity, String dataRange){
        lessonService.insertLesson(lessonEntity, dataRange);
        return ResponseUtil.success();
    }

    /**
     * 新增学生
     */
    @RequestMapping(value = "/class/lesson/students/create", method = RequestMethod.POST)
    public ResponseUtil createLessonStudents(Integer coid, String[] studentList){
        lessonService.insertLessonStudents(coid, studentList);
        return ResponseUtil.success();
    }

    /**
     * 查询班级课节列表
     */
    @RequestMapping(value = "/class/lesson/list",method = RequestMethod.GET)
    public ResponseUtil getClassList(Integer pageNo, Integer pageSize, Integer cid){
        PageHelper.startPage(pageNo, pageSize);
        List<LessonEntity> classList = lessonService.getClassLessonList(cid);
        PageInfo<LessonEntity> pageInfo = new PageInfo<>(classList);
        return ResponseUtil.success(pageInfo);
    }

    /**
     * 查询班级单课节学员列表
     *//*
    @RequestMapping(value = "/class/lesson/student/list/{lessonId}",method = RequestMethod.GET)
    public ResponseUtil getLessonStudentList(@PathVariable(value = "lessonId") Integer lessonId){
        List<StudentEntity> studentEntityList = lessonService.getLessonStudentList(lessonId);
        return ResponseUtil.success(studentEntityList);
    }*/

}