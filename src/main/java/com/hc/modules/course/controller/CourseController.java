package com.hc.modules.course.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.service.CourseService;


import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.teacher.entity.ClassEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 查询课程列表
     */
    @RequestMapping(value = "/course/list",method = RequestMethod.GET)
    public ResponseUtil getCourseList(Integer pageNo, Integer pageSize, HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        PageHelper.startPage(pageNo, pageSize);
        List<CourseEntity> courseList = courseService.getCourseList(token);
        PageInfo<CourseEntity> pageInfo = new PageInfo<>(courseList);
        return ResponseUtil.success(pageInfo);
    }

    /**
     * 新增课程
     */
    @RequestMapping(value = "/course/create", method = RequestMethod.POST)
    public ResponseUtil createCourse(CourseEntity courseEntity,HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        courseService.insertCourse(courseEntity, token);
        return ResponseUtil.success();
    }



}
