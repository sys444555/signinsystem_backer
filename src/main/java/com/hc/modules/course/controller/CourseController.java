package com.hc.modules.course.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.service.CourseService;
import com.hc.modules.teacher.entity.ClassEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CourseController {

    @Resource
    private CourseService courseService;

    /**
     * 新增
     */
    @RequestMapping(value = "/class/course/create", method = RequestMethod.POST)
    public ResponseUtil createCourse(CourseEntity courseEntity, String dataRange){
        courseService.insertCourse(courseEntity, dataRange);
        return ResponseUtil.success();
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/class/course/students/create", method = RequestMethod.POST)
    public ResponseUtil createCourseStudents(Integer coid, String[] studentList){
        courseService.insertCourseStudents(coid, studentList);
        return ResponseUtil.success();
    }

    /**
     * 查询班级课节列表
     */
    @RequestMapping(value = "/class/course/list",method = RequestMethod.GET)
    public ResponseUtil getClassList(Integer pageNo, Integer pageSize, Integer cid){
        PageHelper.startPage(pageNo, pageSize);
        List<CourseEntity> classList = courseService.getClassCourseList(cid);
        PageInfo<CourseEntity> pageInfo = new PageInfo<>(classList);
        return ResponseUtil.success(pageInfo);
    }
}
