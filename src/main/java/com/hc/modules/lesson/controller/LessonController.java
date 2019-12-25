package com.hc.modules.lesson.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.lesson.service.LessonService;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.entity.StudentLessonEntity;
import com.hc.modules.teacher.entity.ClassEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
public class LessonController {

    @Resource
    private LessonService lessonService;

    /**
     * 新增课节
     */
    @RequestMapping(value = "/class/lesson/create", method = RequestMethod.POST)
    public ResponseUtil createLesson(LessonEntity lessonEntity, String dataRange, String timeRange,Integer period, Integer times) throws ParseException {
        lessonService.insertLesson(lessonEntity, dataRange, timeRange,period, times);
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
     *
     *
     */
    @RequestMapping(value = "/class/lesson/student/list/{lessonId}",method = RequestMethod.GET)
    public ResponseUtil getLessonStudentList(@PathVariable(value = "lessonId") Integer lessonId){
        List<StudentLessonEntity> studentEntityList = lessonService.getLessonStudentList(lessonId);
        return ResponseUtil.success(studentEntityList);
    }

    /**
     * 单个学员签到
     */
    @RequestMapping(value = "/class/lesson/student/sign", method = RequestMethod.POST)
    public ResponseUtil lessonSign(Integer lessonId, Integer studentId) throws ParseException {
        lessonService.lessonSign(lessonId, studentId);
        return ResponseUtil.success();
    }

    /**
     * 查看课节
     */
    @RequestMapping(value = "/class/lesson/get/{lessonId}", method = RequestMethod.GET)
    public ResponseUtil lessonSign(@PathVariable(value = "lessonId") Integer lessonId){
        LessonEntity lesson = lessonService.getLesson(lessonId);
        return ResponseUtil.success(lesson);
    }

    /**
     * 查看未加入该课节的学生录
     */
    @RequestMapping(value = "/class/lesson/student/absent/list/{lessonId}",method = RequestMethod.GET)
    public ResponseUtil getLessonAbsentStudentList(@PathVariable(value = "lessonId") Integer lessonId, Integer classId){
        List<StudentEntity> studentEntityList = lessonService.getLessonAbsentStudentList(lessonId, classId);
        return ResponseUtil.success(studentEntityList);
    }

    /**
     * 移除课节学员
     */
    @RequestMapping(value = "/class/lesson/remove/student/{studentId}", method = RequestMethod.POST)
    public ResponseUtil removeLessonStudent(@PathVariable(value = "studentId") Integer studentId, Integer lessonId){
        lessonService.removeLessonStudent(studentId, lessonId);
        return ResponseUtil.success();


    }

}
