package com.hc.modules.course.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.service.CourseService;


import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.teacher.entity.ClassEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 删除课程
     */
    @RequestMapping(value = "/course/delete/{courseId}", method = RequestMethod.POST)
    public ResponseUtil deleteCourse(@PathVariable("courseId") Integer courseId){
        courseService.deleteCourse(courseId);
        return ResponseUtil.success();
    }

    /**
     * 通过id 获取课程
     */
    @RequestMapping(value = "/course/getCourseById/{courseId}", method = RequestMethod.POST)
    public ResponseUtil getCourseById(@PathVariable("courseId") Integer courseId){
        CourseEntity courseEntity = courseService.getCourseById(courseId);
        return ResponseUtil.success(courseEntity);
    }

    /**
     * 更新课包
     */
    @RequestMapping(value = "/course/update", method = RequestMethod.POST)
    public ResponseUtil updateCourse(CourseEntity courseEntity){
       courseService.updateCourse(courseEntity);
        return ResponseUtil.success();
    }


}
